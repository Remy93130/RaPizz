package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.dao.interfaces.DaoPizzaInterface;
import fr.esiee.rapizz.model.Ingredient;
import fr.esiee.rapizz.model.Pizza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPizza extends AbstactDao implements DaoPizzaInterface {
    private static DaoPizza instance = null;

    public static DaoPizza getInstance() {
        if (instance == null) {
            instance = new DaoPizza();
        }
        return instance;
    }

    private DaoPizza() {
        super();
    }

    @Override
    public int add(Pizza pizza) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(pizza.getName());
        params.add(String.valueOf(pizza.getPrice()));
        String sql = "INSERT INTO Pizza (nomPizza, prix) VALUES (?, ?)";
        int id = this.db.preparedRequestInsert(sql, params);
        for (Ingredient ingredient : pizza.getIngredients()) {
            ArrayList<String> paramsIngredient = new ArrayList<>();
            paramsIngredient.add(String.valueOf(id));
            paramsIngredient.add(String.valueOf(ingredient.getId()));
            this.db.preparedRequestInsert("INSERT INTO Contient VALUES (?, ?)", paramsIngredient);
        }
        return id;
    }

    @Override
    public Pizza get(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        String sql = "SELECT * FROM Pizza WHERE idPizza=?";
        ResultSet resultSet = this.db.preparedRequestGet(sql, params);
        if (resultSet.next()) {
            Pizza pizza = new Pizza(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getFloat(3)
            );
            String sqlIngredient = "SELECT * FROM Ingredient NATURAL JOIN Contient WHERE idPizza=?";
            ResultSet resultSetIngredient = this.db.preparedRequestGet(sqlIngredient, params);
            while (resultSetIngredient.next()) {
                pizza.addIngredient(new Ingredient(
                        resultSetIngredient.getInt("idIngredient"),
                        resultSetIngredient.getString("nomIngredient")
                ));
            }
            return pizza;
        }
        return null;
    }

    @Override
    public List<Pizza> get() throws SQLException {
        ArrayList<Pizza> pizzas = new ArrayList<>();
        ResultSet resultSet = this.db.request("SELECT * FROM Pizza");
        while (resultSet.next()) {
            pizzas.add(new Pizza(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getFloat(3)
            ));
        }
        for (Pizza pizza : pizzas) {
            ArrayList<String> params = new ArrayList<>();
            params.add((String.valueOf(pizza.getId())));
            String sqlIngredient = "SELECT * FROM Ingredient NATURAL JOIN Contient WHERE idPizza=?";
            ResultSet resultSetIngredient = this.db.preparedRequestGet(sqlIngredient, params);
            while (resultSetIngredient.next()) {
                pizza.addIngredient(new Ingredient(
                        resultSetIngredient.getInt("idIngredient"),
                        resultSetIngredient.getString("nomIngredient")
                ));
            }
        }
        return pizzas;
    }

    @Override
    public boolean update(Pizza pizza) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(pizza.getName());
        params.add(String.valueOf(pizza.getPrice()));
        params.add(String.valueOf(pizza.getId()));
        String sql = "UPDATE Pizza SET nomPizza=?, prix=? WHERE idPizza=?";
        return this.db.preparedRequestUpdate(sql, params);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        boolean deleteContient = this.db.preparedRequestUpdate("DELETE FROM Contient  WHERE idPizza=?", params);
        boolean deletePizza = this.db.preparedRequestUpdate("DELETE FROM Pizza WHERE idPizza=?", params);
        return deleteContient && deletePizza;
    }
}
