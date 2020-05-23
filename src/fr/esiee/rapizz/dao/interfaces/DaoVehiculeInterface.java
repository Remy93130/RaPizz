package fr.esiee.rapizz.dao.interfaces;

import fr.esiee.rapizz.model.Vehicule;

import java.sql.SQLException;
import java.util.List;

public interface DaoVehiculeInterface {
    public int add(Vehicule vehicule) throws SQLException;

    public Vehicule get(int id) throws SQLException;

    public List<Vehicule> get() throws SQLException;

    public boolean update(Vehicule vehicule) throws SQLException;

    public boolean delete(int id) throws SQLException;
}
