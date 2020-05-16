package fr.esiee.rapizz.model;

import java.util.Objects;

public class Taille {
    private int id;
    private float ratio;

    public Taille(int id, float ratio) {
        this.id = id;
        this.ratio = ratio;
    }

    public int getId() {
        return id;
    }

    public Taille setId(int id) {
        this.id = id;
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
        return Float.compare(taille.ratio, ratio) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratio);
    }

    @Override
    public String toString() {
        return "Taille{" +
                "id=" + id +
                ", ratio=" + ratio +
                '}';
    }
}
