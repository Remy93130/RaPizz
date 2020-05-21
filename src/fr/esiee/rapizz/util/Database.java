package fr.esiee.rapizz.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private Connection con = null;
    private static Database instance = null;

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

    public static Connection getConnection() {
        if (Database.instance == null) {
            Database.instance = new Database();
        }
        return Database.instance.con;
    }
}
