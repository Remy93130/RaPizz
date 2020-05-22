package fr.esiee.rapizz.dao.interfaces;

import fr.esiee.rapizz.model.Livreur;

import java.sql.SQLException;
import java.util.List;

public interface DaoLivreurInterface {
    public int add(Livreur livreur) throws SQLException;

    public Livreur get(int id) throws SQLException;

    public List<Livreur> get() throws SQLException;

    public boolean update(Livreur livreur) throws SQLException;

    public boolean delete(int id) throws SQLException;
}
