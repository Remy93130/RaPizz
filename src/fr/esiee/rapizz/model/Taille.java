package fr.esiee.rapizz.model;

import java.util.Objects;

public class Taille {
    private String id;
    private float ratio;

    public Taille(String id, float ratio) {
        this.id = Objects.requireNonNull(id);
        this.ratio = ratio;
    }

    public String getId() {
        return id;
    }

    public Taille setId(String id) {
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
        return Float.compare(taille.getRatio(), getRatio()) == 0 &&
                getId().equals(taille.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRatio());
    }

    @Override
    public String toString() {
        return "Taille{" +
                "id=" + id +
                ", ratio=" + ratio +
                '}';
    }
}
