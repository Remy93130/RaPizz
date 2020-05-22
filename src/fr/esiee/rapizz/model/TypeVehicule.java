package fr.esiee.rapizz.model;

import java.util.Objects;

public class TypeVehicule {
    private int id;
    private String name;

    public TypeVehicule(int id, String name) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
    }

    public TypeVehicule(String name) {
        this(-1, name);
    }

    public int getId() {
        return id;
    }

    public TypeVehicule setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TypeVehicule setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeVehicule that = (TypeVehicule) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TypeVehicule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
