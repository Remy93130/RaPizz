package fr.esiee.rapizz.dao.interfaces;

import fr.esiee.rapizz.model.Client;

import java.util.List;

public interface DaoClientInterface {
    public int add(Client client);

    public Client get(int id);

    public List<Client> get();

    public boolean update(Client client);

    public boolean delete(int id);
}
