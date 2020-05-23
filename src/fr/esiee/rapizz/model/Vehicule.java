package fr.esiee.rapizz.model;

import java.util.Objects;

public class Vehicule {
    private int id;
    private String immatriculation;
    private TypeVehicule typeVehicule;

    public Vehicule(int id, String immatriculation, TypeVehicule typeVehicule) {
        this.id = id;
        this.immatriculation = Objects.requireNonNull(immatriculation);
        this.typeVehicule = Objects.requireNonNull(typeVehicule);
    }

    public Vehicule(String immatriculation, TypeVehicule typeVehicule) {
        this(-1, immatriculation, typeVehicule);
    }

    public int getId() {
        return id;
    }

    public Vehicule setId(int id) {
        this.id = id;
        return this;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public Vehicule setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
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
        return getImmatriculation().equals(vehicule.getImmatriculation()) &&
                getTypeVehicule().equals(vehicule.getTypeVehicule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImmatriculation(), getTypeVehicule());
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", immatriculation='" + immatriculation + '\'' +
                ", typeVehicule=" + typeVehicule +
                '}';
    }
}
