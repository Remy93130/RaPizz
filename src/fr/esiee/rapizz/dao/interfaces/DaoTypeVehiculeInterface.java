package fr.esiee.rapizz.dao.interfaces;

import fr.esiee.rapizz.model.TypeVehicule;

import java.sql.SQLException;
import java.util.List;

public interface DaoTypeVehiculeInterface {

    public int add(TypeVehicule typeVehicule) throws SQLException;

    public TypeVehicule get(int id) throws SQLException;

    public List<TypeVehicule> get() throws SQLException;

    public boolean update(TypeVehicule typeVehicule) throws SQLException;

    public boolean delete(int id) throws SQLException;
}
