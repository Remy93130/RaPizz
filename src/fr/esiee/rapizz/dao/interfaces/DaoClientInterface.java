package fr.esiee.rapizz.dao.interfaces;

import fr.esiee.rapizz.model.Client;

import java.sql.SQLException;
import java.util.List;

public interface DaoClientInterface {
    public int add(Client client) throws SQLException;

    public Client get(int id) throws SQLException;

    public List<Client> get() throws SQLException;

    public boolean update(Client client) throws SQLException;

    public boolean delete(int id) throws SQLException;
}
