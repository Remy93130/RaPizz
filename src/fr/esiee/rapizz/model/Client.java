package fr.esiee.rapizz.model;

import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private int sold;
    private int fidelity;
    private Adresse adresse;

    public Client(int id, String name, int sold, int fidelity, Adresse adresse) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.sold = sold;
        this.fidelity = fidelity;
        this.adresse = Objects.requireNonNull(adresse);
    }

    public int getId() {
        return id;
    }

    public Client setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public int getSold() {
        return sold;
    }

    public Client setSold(int sold) {
        this.sold = sold;
        return this;
    }

    public int getFidelity() {
        return fidelity;
    }

    public Client setFidelity(int fidelity) {
        this.fidelity = fidelity;
        return this;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public Client setAdresse(Adresse adresse) {
        this.adresse = adresse;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return sold == client.sold &&
                fidelity == client.fidelity &&
                name.equals(client.name) &&
                adresse.equals(client.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sold, fidelity, adresse);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sold=" + sold +
                ", fidelity=" + fidelity +
                ", adresse=" + adresse +
                '}';
    }
}
