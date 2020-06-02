package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.dao.interfaces.DaoClientInterface;
import fr.esiee.rapizz.model.Adresse;
import fr.esiee.rapizz.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoClient extends AbstactDao implements DaoClientInterface {
    private static DaoClient instance = null;

    public static DaoClient getInstance() {
        if (instance == null) {
            instance = new DaoClient();
        }
        return instance;
    }

    private DaoClient() {
        super();
    }

    @Override
    public int add(Client client) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(client.getName());
        params.add(String.valueOf(client.getSold()));
        params.add(String.valueOf(client.getFidelity()));
        params.add(String.valueOf(client.getAdresse().getId()));
        String sql = "INSERT INTO Client (nomClient, solde, pointFidelite, idAdresse)";
        sql += "VALUES (?, ?, ?, ?)";
        return this.db.preparedRequestInsert(sql, params);
    }

    @Override
    public Client get(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        String sql = "SELECT * FROM Client NATURAL JOIN Adresse WHERE idClient=?";
        ResultSet resultSet = this.db.preparedRequestGet(sql, params);
        if (resultSet.next()) {
            return new Client(
                    resultSet.getInt("idClient"),
                    resultSet.getString("nomClient"),
                    resultSet.getFloat("solde"),
                    resultSet.getInt("pointFidelite"),
                    new Adresse(
                            resultSet.getInt("idAdresse"),
                            resultSet.getString("ville"),
                            resultSet.getString("rue"),
                            resultSet.getInt("numero"),
                            resultSet.getString("codePostal")
                    )
            );
        }
        return null;
    }

    @Override
    public List<Client> get() throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();
        ResultSet resultSet = this.db.request("SELECT * FROM Client NATURAL JOIN Adresse");
        while (resultSet.next()) {
            clients.add(new Client(
                    resultSet.getInt("idClient"),
                    resultSet.getString("nomClient"),
                    resultSet.getFloat("solde"),
                    resultSet.getInt("pointFidelite"),
                    new Adresse(
                            resultSet.getInt("idAdresse"),
                            resultSet.getString("ville"),
                            resultSet.getString("rue"),
                            resultSet.getInt("numero"),
                            resultSet.getString("codePostal")
                    )
            ));
        }
        return clients;
    }

    @Override
    public boolean update(Client client) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(client.getName());
        params.add(String.valueOf(client.getSold()));
        params.add(String.valueOf(client.getFidelity()));
        params.add(String.valueOf(client.getAdresse().getId()));
        params.add(String.valueOf(client.getId()));
        String sql = "UPDATE Client SET nomClient=?, solde=?, pointFidelite=?, idAdresse=? WHERE idClient=?";
        return this.db.preparedRequestUpdate(sql, params);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        return this.db.preparedRequestUpdate("DELETE FROM Client WHERE idClient=?", params);
    }
}
