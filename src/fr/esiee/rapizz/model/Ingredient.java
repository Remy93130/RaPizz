package fr.esiee.rapizz.model;

import java.util.ArrayList;
import java.util.Objects;

public class Ingredient {
    private int id;
    private String name;
    private ArrayList<Pizza> pizzas;

    public Ingredient(int id, String name) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.pizzas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Ingredient setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Ingredient setName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public Ingredient addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
        return this;
    }

    public Ingredient removePizza(Pizza pizza) {
        this.pizzas.remove(pizza);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
