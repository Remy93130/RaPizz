package fr.esiee.rapizz.model;

import java.util.Date;
import java.util.Objects;

public class DeliveryTicket {
    private final int id;
    private final String livreur;
    private final String vehicule;
    private final String clientName;
    private final Long retard;
    private final String pizzaName;
    private final float price;

    public DeliveryTicket(int id, String livreur, String vehicule, String clientName, Date retard, String pizzaName, float price) {
        this.id = id;
        this.livreur = Objects.requireNonNull(livreur);
        this.vehicule = Objects.requireNonNull(vehicule);
        this.clientName = Objects.requireNonNull(clientName);
        this.retard = (retard != null)? retard.getTime() / 1000 : null;
        this.pizzaName = Objects.requireNonNull(pizzaName);
        if (this.retard != null && this.retard >= 30 * 60) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public int getId() {
        return id;
    }

    public String getLivreur() {
        return livreur;
    }

    public String getVehicule() {
        return vehicule;
    }

    public String getClientName() {
        return clientName;
    }

    public Long getRetard() {
        return retard;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryTicket that = (DeliveryTicket) o;
        return getId() == that.getId() &&
                Float.compare(that.getPrice(), getPrice()) == 0 &&
                getLivreur().equals(that.getLivreur()) &&
                getVehicule().equals(that.getVehicule()) &&
                getClientName().equals(that.getClientName()) &&
                Objects.equals(getRetard(), that.getRetard()) &&
                getPizzaName().equals(that.getPizzaName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLivreur(), getVehicule(), getClientName(), getRetard(), getPizzaName(), getPrice());
    }

    @Override
    public String toString() {
        return "DeliveryTicket{" +
                "id=" + id +
                ", livreur='" + livreur + '\'' +
                ", vehicule='" + vehicule + '\'' +
                ", clientName='" + clientName + '\'' +
                ", retard=" + retard +
                ", pizzaName='" + pizzaName + '\'' +
                ", price=" + price +
                '}';
    }
}
