package fr.esiee.rapizz.model;

import java.util.Date;
import java.util.Objects;

public class Commande {
    private int id;
    private Date dateCommand;
    private Date dateDelivery;
    private Client client;
    private Pizza pizza;
    private Taille taille;
    private Livreur livreur;
    private Vehicule vehicule;

    public Commande(int id, Date dateCommand, Date dateDelivery, Client client, Pizza pizza, Taille taille, Livreur livreur, Vehicule vehicule) {
        this.id = id;
        this.dateCommand = Objects.requireNonNull(dateCommand);
        this.dateDelivery = Objects.requireNonNull(dateDelivery);
        this.client = Objects.requireNonNull(client);
        this.pizza = Objects.requireNonNull(pizza);
        this.taille = Objects.requireNonNull(taille);
        this.livreur = Objects.requireNonNull(livreur);
        this.vehicule = Objects.requireNonNull(vehicule);
    }

    public int getId() {
        return id;
    }

    public Commande setId(int id) {
        this.id = id;
        return this;
    }

    public Date getDateCommand() {
        return dateCommand;
    }

    public Commande setDateCommand(Date dateCommand) {
        this.dateCommand = dateCommand;
        return this;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public Commande setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Commande setClient(Client client) {
        this.client = client;
        return this;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public Commande setPizza(Pizza pizza) {
        this.pizza = pizza;
        return this;
    }

    public Taille getTaille() {
        return taille;
    }

    public Commande setTaille(Taille taille) {
        this.taille = taille;
        return this;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public Commande setLivreur(Livreur livreur) {
        this.livreur = livreur;
        return this;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public Commande setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return dateCommand.equals(commande.dateCommand) &&
                dateDelivery.equals(commande.dateDelivery) &&
                client.equals(commande.client) &&
                pizza.equals(commande.pizza) &&
                taille.equals(commande.taille) &&
                livreur.equals(commande.livreur) &&
                vehicule.equals(commande.vehicule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateCommand, dateDelivery, client, pizza, taille, livreur, vehicule);
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", dateCommand=" + dateCommand +
                ", dateDelivery=" + dateDelivery +
                ", client=" + client +
                ", pizza=" + pizza +
                ", taille=" + taille +
                ", livreur=" + livreur +
                ", vehicule=" + vehicule +
                '}';
    }
}
