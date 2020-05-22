package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.dao.interfaces.DaoLivreurInterface;
import fr.esiee.rapizz.model.Livreur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoLivreur extends AbstactDao implements DaoLivreurInterface {
    @Override
    public int add(Livreur livreur) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(livreur.getName());
        String sql = "INSERT INTO Livreur (nomLivreur) VALUES (?)";
        return this.db.preparedRequestInsert(sql, params);
    }

    @Override
    public Livreur get(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        ResultSet resultSet = this.db.preparedRequestGet("SELECT * FROM Livreur WHERE idLivreur=?", params);
        if (resultSet.next()) {
            return new Livreur(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }

    @Override
    public List<Livreur> get() throws SQLException {
        ArrayList<Livreur> livreurs = new ArrayList<>();
        ResultSet resultSet = this.db.request("SELECT * FROM Livreur");
        while (resultSet.next()) {
            livreurs.add(new Livreur(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            ));
        }
        return livreurs;
    }

    @Override
    public boolean update(Livreur livreur) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(livreur.getName());
        params.add(String.valueOf(livreur.getId()));
        String sql = "UPDATE Livreur SET nomLivreur=? WHERE idLivreur=?";
        return this.db.preparedRequestUpdate(sql, params);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        return this.db.preparedRequestUpdate("DELETE FROM Livreur WHERE idLivreur=?", params);
    }
}
