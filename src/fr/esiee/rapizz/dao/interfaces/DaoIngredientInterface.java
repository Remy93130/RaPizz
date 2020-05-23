package fr.esiee.rapizz.dao.interfaces;

import fr.esiee.rapizz.model.Ingredient;

import java.sql.SQLException;
import java.util.List;

public interface DaoIngredientInterface {
    public int add(Ingredient ingredient) throws SQLException;

    public Ingredient get(int id) throws SQLException;

    public List<Ingredient> get() throws SQLException;

    public boolean update(Ingredient ingredient) throws SQLException;

    public boolean delete(int id) throws SQLException;
}
