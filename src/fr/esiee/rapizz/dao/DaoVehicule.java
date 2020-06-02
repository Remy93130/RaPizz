package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.dao.interfaces.DaoVehiculeInterface;
import fr.esiee.rapizz.model.TypeVehicule;
import fr.esiee.rapizz.model.Vehicule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoVehicule extends AbstactDao implements DaoVehiculeInterface {
    private static DaoVehicule instance = null;

    public static DaoVehicule getInstance() {
        if (instance == null) {
            instance = new DaoVehicule();
        }
        return instance;
    }

    private DaoVehicule() {
        super();
    }

    @Override
    public int add(Vehicule vehicule) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(vehicule.getImmatriculation());
        params.add(String.valueOf(vehicule.getTypeVehicule().getId()));
        String sql = "INSERT INTO Vehicule (plaqueImmat, idTypeVehicule) VALUES (?, ?)";
        return this.db.preparedRequestInsert(sql, params);
    }

    @Override
    public Vehicule get(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        String sql = "SELECT * FROM Vehicule NATURAL JOIN TypeVehicule WHERE idVehicule=?";
        ResultSet resultSet = this.db.preparedRequestGet(sql, params);
        if (resultSet.next()) {
            return new Vehicule(
                    resultSet.getInt("idVehicule"),
                    resultSet.getString("plaqueImmat"),
                    new TypeVehicule(
                            resultSet.getInt("idTypeVehicule"),
                            resultSet.getString("nomTypeVehicule")
                    )
            );
        }
        return null;
    }

    @Override
    public List<Vehicule> get() throws SQLException {
        ArrayList<Vehicule> vehicules = new ArrayList<>();
        ResultSet resultSet = this.db.request("SELECT * FROM Vehicule NATURAL JOIN TypeVehicule");
        while (resultSet.next()) {
            vehicules.add(new Vehicule(
                    resultSet.getInt("idVehicule"),
                    resultSet.getString("plaqueImmat"),
                    new TypeVehicule(
                            resultSet.getInt("idTypeVehicule"),
                            resultSet.getString("nomTypeVehicule")
                    )
            ));
        }
        return vehicules;
    }

    @Override
    public boolean update(Vehicule vehicule) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(vehicule.getImmatriculation());
        params.add(String.valueOf(vehicule.getTypeVehicule().getId()));
        params.add(String.valueOf(vehicule.getId()));
        String sql = "UPDATE Vehicule SET plaqueImmat=?, idTypeVehicule=? WHERE idVehicule=?";
        return this.db.preparedRequestUpdate(sql, params);

    }

    @Override
    public boolean delete(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        return this.db.preparedRequestUpdate("DELETE FROM Vehicule WHERE idVehicule=?", params);
    }
}
