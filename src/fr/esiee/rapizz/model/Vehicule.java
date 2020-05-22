package fr.esiee.rapizz.model;

import java.util.Objects;

public class Vehicule {
    private int id;
    private TypeVehicule typeVehicule;

    public Vehicule(int id, TypeVehicule typeVehicule) {
        this.id = id;
        this.typeVehicule = Objects.requireNonNull(typeVehicule);
    }

    public Vehicule(TypeVehicule typeVehicule) {
        this(-1, typeVehicule);
    }

    public int getId() {
        return id;
    }

    public Vehicule setId(int id) {
        this.id = id;
        return this;
    }

    public TypeVehicule getTypeVehicule() {
        return typeVehicule;
    }

    public Vehicule setTypeVehicule(TypeVehicule typeVehicule) {
        this.typeVehicule = typeVehicule;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicule vehicule = (Vehicule) o;
        return typeVehicule.equals(vehicule.typeVehicule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeVehicule);
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", typeVehicule=" + typeVehicule +
                '}';
    }
}
