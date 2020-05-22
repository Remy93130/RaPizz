package fr.esiee.rapizz.model;

import java.util.Objects;

public class Livreur {
    private int id;
    private String name;

    public Livreur(int id, String name) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
    }

    public Livreur(String name) {
        this(-1, name);
    }

    public int getId() {
        return id;
    }

    public Livreur setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Livreur setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livreur livreur = (Livreur) o;
        return name.equals(livreur.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Livreur{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
