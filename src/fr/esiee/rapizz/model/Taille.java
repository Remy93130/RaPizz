package fr.esiee.rapizz.model;

import java.util.Objects;

public class Taille {
    private int id;
    private String name;
    private float ratio;

    public Taille(int id, String name, float ratio) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.ratio = ratio;
    }

    public Taille(String name, float ratio) {
        this(-1, name, ratio);
    }

    public int getId() {
        return id;
    }

    public Taille setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Taille setName(String name) {
        this.name = name;
        return this;
    }

    public float getRatio() {
        return ratio;
    }

    public Taille setRatio(float ratio) {
        this.ratio = ratio;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taille taille = (Taille) o;
        return Float.compare(taille.getRatio(), getRatio()) == 0 &&
                getName().equals(taille.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRatio());
    }

    @Override
    public String toString() {
        return "Taille{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ratio=" + ratio +
                '}';
    }
}
