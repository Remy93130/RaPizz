package fr.esiee.rapizz.dao.interfaces;

import fr.esiee.rapizz.model.Commande;

import java.sql.SQLException;
import java.util.List;

public interface DaoCommandeInterface {
    public int add(Commande commande) throws SQLException;

    public Commande get(int id) throws SQLException;

    public List<Commande> get() throws SQLException;

    public boolean update(Commande commande) throws SQLException;

    public boolean delete(int id) throws SQLException;
}
