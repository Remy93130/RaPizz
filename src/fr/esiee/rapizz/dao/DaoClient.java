package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.dao.interfaces.DaoClientInterface;
import fr.esiee.rapizz.model.Client;
import fr.esiee.rapizz.util.Database;

import java.sql.Connection;
import java.util.List;

public class DaoClient implements DaoClientInterface {

    @Override
    public int add(Client client) {
        return 0;
    }

    @Override
    public Client get(int id) {
        return null;
    }

    @Override
    public List<Client> get() {
        return null;
    }

    @Override
    public boolean update(Client client) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
