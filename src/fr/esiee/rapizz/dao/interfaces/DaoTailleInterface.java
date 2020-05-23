package fr.esiee.rapizz.dao.interfaces;

import fr.esiee.rapizz.model.Taille;

import java.sql.SQLException;
import java.util.List;

public interface DaoTailleInterface {
    public int add(Taille taille) throws SQLException;

    public Taille get(int id) throws SQLException;

    public List<Taille> get() throws SQLException;

    public boolean update(Taille taille) throws SQLException;

    public boolean delete(int id) throws SQLException;
}
