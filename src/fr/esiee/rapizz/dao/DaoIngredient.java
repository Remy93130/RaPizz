package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.dao.interfaces.DaoIngredientInterface;
import fr.esiee.rapizz.model.Ingredient;
import fr.esiee.rapizz.model.Pizza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoIngredient extends AbstactDao implements DaoIngredientInterface {
    @Override
    public int add(Ingredient ingredient) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(ingredient.getName());
        String sql = "INSERT INTO ingredient (nomIngredient) VALUES (?)";
        int id = this.db.preparedRequestInsert(sql, params);
        for (Pizza pizza : ingredient.getPizzas()) {
            ArrayList<String> paramsPizza = new ArrayList<>();
            paramsPizza.add(String.valueOf(pizza.getId()));
            paramsPizza.add(String.valueOf(id));
            this.db.preparedRequestInsert("INSERT INTO Contient VALUES (?, ?)", paramsPizza);
        }
        return id;
    }

    @Override
    public Ingredient get(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add((String.valueOf(id)));
        String sql = "SELECT * FROM Ingredient WHERE idIngredient=?";
        ResultSet resultSet = this.db.preparedRequestGet(sql, params);
        if (resultSet.next()) {
            Ingredient ingredient = new Ingredient(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            );
            String sqlPizza = "SELECT * FROM Pizza NATURAL JOIN Contient WHERE idIngredient=?";
            ResultSet resultSetPizza = this.db.preparedRequestGet(sqlPizza, params);
            while (resultSetPizza.next()) {
                ingredient.addPizza(new Pizza(
                        resultSetPizza.getInt("idPizza"),
                        resultSetPizza.getString("nomPizza"),
                        resultSetPizza.getFloat("prix")
                ));
            }
            return ingredient;
        }
        return null;
    }

    @Override
    public List<Ingredient> get() throws SQLException {
        ArrayList<Ingredient> ingredients =  new ArrayList<>();
        ResultSet resultSet = this.db.request("SELECT * FROM Ingredient");
        while (resultSet.next()) {
            ingredients.add(new Ingredient(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            ));
        }
        for (Ingredient ingredient: ingredients) {
            ArrayList<String> params = new ArrayList<>();
            params.add(String.valueOf(ingredient.getId()));
            String sqlPizza = "SELECT * FROM Pizza NATURAL JOIN Contient WHERE idIngredient=?";
            ResultSet resultSetPizza = this.db.preparedRequestGet(sqlPizza, params);
            while (resultSetPizza.next()) {
                ingredient.addPizza(new Pizza(
                        resultSetPizza.getInt("idPizza"),
                        resultSetPizza.getString("nomPizza"),
                        resultSetPizza.getFloat("prix")
                ));
            }
        }
        return ingredients;
    }

    @Override
    public boolean update(Ingredient ingredient) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(ingredient.getName());
        params.add(String.valueOf(ingredient.getId()));
        String sql = "UPDATE Ingredient SET nomIngredient=? WHERE idIngredient=?";
        return this.db.preparedRequestUpdate(sql, params);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        boolean deleteContient = this.db.preparedRequestUpdate("DELETE FROM Contient WHERE idIngredient=?", params);
        boolean deleteIngr = this.db.preparedRequestUpdate("DELETE FROM Ingredient WHERE idIngredient=?", params);
        return deleteContient && deleteIngr;
    }
}
