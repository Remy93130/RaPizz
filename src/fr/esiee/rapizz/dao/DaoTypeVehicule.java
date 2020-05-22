package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.dao.interfaces.DaoTypeVehiculeInterface;
import fr.esiee.rapizz.model.TypeVehicule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTypeVehicule extends AbstactDao implements DaoTypeVehiculeInterface {
    @Override
    public int add(TypeVehicule typeVehicule) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(typeVehicule.getName());
        String sql = "INSERT INTO TypeVehicule (nomTypeVehicule) VALUES (?)";
        return this.db.preparedRequestInsert(sql, params);
    }

    @Override
    public TypeVehicule get(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        String sql = "SELECT * FROM TypeVehicule WHERE idTypeVehicule=?";
        ResultSet resultSet = this.db.preparedRequestGet(sql, params);
        if (resultSet.next()) {
            return new TypeVehicule(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }

    @Override
    public List<TypeVehicule> get() throws SQLException {
        ArrayList<TypeVehicule> typeVehicules = new ArrayList<>();
        ResultSet resultSet = this.db.request("SELECT * FROM TypeVehicule");
        while (resultSet.next()) {
            typeVehicules.add(new TypeVehicule(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            ));
        }
        return typeVehicules;
    }

    @Override
    public boolean update(TypeVehicule typeVehicule) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(typeVehicule.getName());
        params.add(String.valueOf(typeVehicule.getId()));
        String sql = "UPDATE TypeVehicule SET nomTypeVehicule=? WHERE idTypeVehicule=?";
        return this.db.preparedRequestUpdate(sql, params);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        String sql = "DELETE FROM TypeVehicule WHERE idTypeVehicule=?";
        return this.db.preparedRequestUpdate(sql, params);
    }
}
