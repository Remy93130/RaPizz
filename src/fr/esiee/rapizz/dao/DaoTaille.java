package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.dao.interfaces.DaoTailleInterface;
import fr.esiee.rapizz.model.Taille;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTaille extends AbstactDao implements DaoTailleInterface {
    private static DaoTaille instance = null;

    public static DaoTaille getInstance() {
        if (instance == null) {
            instance = new DaoTaille();
        }
        return instance;
    }

    private DaoTaille() {
        super();
    }

    @Override
    public int add(Taille taille) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(taille.getName()));
        params.add(String.valueOf(taille.getRatio()));
        String sql = "INSERT INTO Taille (nomTaille, ratioPrix) VALUES (?, ?)";
        return this.db.preparedRequestInsert(sql, params);
    }

    @Override
    public Taille get(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        String sql = "SELECT * FROM Taille WHERE idTaille=?";
        ResultSet resultSet = this.db.preparedRequestGet(sql, params);
        if (resultSet.next()) {
            return new Taille(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getFloat(3)
            );
        }
        return null;
    }

    @Override
    public List<Taille> get() throws SQLException {
        ArrayList<Taille> tailles = new ArrayList<>();
        ResultSet resultSet = this.db.request("SELECT * FROM Taille");
        while (resultSet.next()) {
            tailles.add(new Taille(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getFloat(3)
            ));
        }
        return tailles;
    }

    @Override
    public boolean update(Taille taille) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(taille.getName()));
        params.add(String.valueOf(taille.getRatio()));
        params.add(String.valueOf(taille.getId()));
        String sql = "UPDATE Taille SET nomTaille=?, ratioPrix=? WHERE idTaille=?";
        return this.db.preparedRequestUpdate(sql, params);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        String sql = "DELETE FROM Taille WHERE idTaille=?";
        return this.db.preparedRequestUpdate(sql, params);
    }
}
