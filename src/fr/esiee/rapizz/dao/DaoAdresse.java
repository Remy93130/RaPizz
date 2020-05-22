package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.dao.interfaces.DaoAdresseInterface;
import fr.esiee.rapizz.model.Adresse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoAdresse extends AbstactDao implements DaoAdresseInterface {
    @Override
    public int add(Adresse adresse) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(adresse.getCity());
        params.add(adresse.getRoad());
        params.add(String.valueOf(adresse.getNumber()));
        params.add(adresse.getZipCode());
        String sql = "INSERT INTO Adresse (ville, rue, numero, codePostal) VALUES (?, ?, ?, ?);";
        return this.db.preparedRequestInsert(sql, params);
    }

    @Override
    public Adresse get(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        ResultSet resultSet = this.db.preparedRequestGet("SELECT * FROM Adresse WHERE idAdresse=?", params);
        if (resultSet.next()) {
            return new Adresse(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    @Override
    public List<Adresse> get() throws SQLException {
        ArrayList<Adresse> adresses = new ArrayList<>();
        ResultSet resultSet = this.db.request("SELECT * FROM Adresse");
        while (resultSet.next()) {
            adresses.add(new Adresse(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)
            ));
        }
        return adresses;
    }

    @Override
    public boolean update(Adresse adresse) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(adresse.getCity());
        params.add(adresse.getRoad());
        params.add(String.valueOf(adresse.getNumber()));
        params.add(adresse.getZipCode());
        params.add(String.valueOf(adresse.getId()));
        String sql = "UPDATE Adresse SET ville=?, rue=?, numero=?, codePostal=? WHERE idAdresse=?";
        return this.db.preparedRequestUpdate(sql, params);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        return this.db.preparedRequestUpdate("DELETE FROM Adresse WHERE idAdresse=?", params);
    }
}
