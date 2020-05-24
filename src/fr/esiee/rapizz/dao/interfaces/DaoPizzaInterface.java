package fr.esiee.rapizz.dao.interfaces;

import fr.esiee.rapizz.model.Pizza;

import java.sql.SQLException;
import java.util.List;

public interface DaoPizzaInterface {
    public int add(Pizza pizza) throws SQLException;

    public Pizza get(int id) throws SQLException;

    public List<Pizza> get() throws SQLException;

    public boolean update(Pizza pizza) throws SQLException;

    public boolean delete(int id) throws SQLException;
}
