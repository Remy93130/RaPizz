package fr.esiee.rapizz.model;

import java.util.ArrayList;
import java.util.Objects;

public class Pizza {
    private int id;
    private String name;
    private float price;
    private ArrayList<Ingredient> ingredients;


    public Pizza(int id, String name, float price) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.price = price;
        this.ingredients = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public Pizza setId(int id) {
        this.id = id;
        return this;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Pizza setName(String name) {
        this.name = name;
        return this;
    }

    public Pizza setPrice(float price) {
        this.price = price;
        return this;
    }

    public Pizza addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public Pizza removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Float.compare(pizza.price, price) == 0 &&
                name.equals(pizza.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
