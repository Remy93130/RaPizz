package fr.esiee.rapizz.dao.interfaces;

import fr.esiee.rapizz.model.Adresse;

import java.sql.SQLException;
import java.util.List;

public interface DaoAdresseInterface {
    public int add(Adresse adresse) throws SQLException;

    public Adresse get(int id) throws SQLException;

    public List<Adresse> get() throws SQLException;

    public boolean update(Adresse adresse) throws SQLException;

    public boolean delete(int id) throws SQLException;


}
