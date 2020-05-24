package fr.esiee.rapizz.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class Database {
    private static Database instance = null;
    private Connection con = null;

    /**
     * Singleton constructor
     */
    private Database() {
        HashMap<String, String> data = this.getCredentials();
        String url = String.format("jdbc:mysql://localhost:3306/%s", data.get("dbName"));
        String username = data.get("dbUsername");
        String password = data.get("dbPassword");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The singleton getter
     *
     * @return Database object
     */
    public static Database getDatabase() {
        if (Database.instance == null) {
            Database.instance = new Database();
        }
        return Database.instance;
    }

    /**
     * Read the json file with the database credentials
     *
     * @return An hashmap with key and value as string
     */
    private HashMap<String, String> getCredentials() {
        HashMap<String, String> data = new HashMap<>();
        try {
            Object obj = new JSONParser().parse(new FileReader("./env.json"));
            JSONObject jsonData = (JSONObject) obj;
            data.put("dbName", (String) jsonData.get("db_name"));
            data.put("dbUsername", (String) jsonData.get("db_username"));
            data.put("dbPassword", (String) jsonData.get("db_password"));
        } catch (ParseException e) {
            System.err.println("Erreur de format dans le fichier JSON");
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println("Impossible de lire le fichier environement");
            System.err.println(e.toString());
        }
        return data;
    }

    /**
     * Request who display tables present in the database
     *
     * @throws SQLException If there are trouble during the request
     */
    public void requestTest() throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery("SHOW TABLES");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }

    /**
     * Create and execute a prepared request and return the result in a ResultSet object
     *
     * @param sql        The statment
     * @param parameters Parameters to bind
     * @return ResultSet
     * @throws SQLException If there are trouble during the request
     */
    public ResultSet preparedRequestGet(String sql, List<String> parameters) throws SQLException {
        PreparedStatement statement = con.prepareStatement(sql);
        for (int i = 0; i < parameters.size(); i++) {
            statement.setString(i + 1, parameters.get(i));
        }
        return statement.executeQuery();
    }

    /**
     * Create and execute a prepared request to insert data and return the new id
     *
     * @param sql        The statment
     * @param parameters Parameters to bind
     * @return The id of the entry
     * @throws SQLException If there are trouble during the request
     */
    public int preparedRequestInsert(String sql, List<String> parameters) throws SQLException {
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < parameters.size(); i++) {
            statement.setString(i + 1, parameters.get(i));
        }
        statement.executeUpdate();
        ResultSet generatedKey = statement.getGeneratedKeys();
        generatedKey.next();
        return (int) generatedKey.getLong(1);
    }

    public boolean preparedRequestUpdate(String sql, List<String> parameters) throws SQLException {
        PreparedStatement statement = con.prepareStatement(sql);
        for (int i = 0; i < parameters.size(); i++) {
            statement.setString(i + 1, parameters.get(i));
        }
        return statement.executeUpdate() > 0;
    }

    /**
     * Just a request method
     *
     * @param sql The statment
     * @return ResultSet
     * @throws SQLException If there are trouble during the request
     */
    public ResultSet request(String sql) throws SQLException {
        return con.createStatement().executeQuery(sql);
    }
}
