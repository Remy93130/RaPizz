package fr.esiee.rapizz.main;

import fr.esiee.rapizz.dao.*;
import fr.esiee.rapizz.model.*;

import java.sql.SQLException;
import java.util.Date;

public class Application {
    public static void main(String[] args) {
        try {
            /*Application.adresse();
            Application.client();
            Application.livreur();
            Application.typeVehicule();
            Application.taille();
            Application.vehicule();
            Application.ingredient();
            Application.pizza();
            Application.commande();*/
            Application.special();
        } catch (SQLException e) {
            displaySQLException(e);
        }
    }

    private static void adresse() throws SQLException {
        DaoAdresse daoAdresse = DaoAdresse.getInstance();
        System.out.println("Adresse:");
        daoAdresse.get().forEach(System.out::println);
        int res = daoAdresse.add(new Adresse(
                "CityTest",
                "RoadTest",
                111,
                "12345"
        ));
        Adresse adresse = daoAdresse.get(res);
        System.out.println("After add:\n" + adresse);
        adresse
                .setCity("Updated City")
                .setRoad("Updated Road");
        daoAdresse.update(adresse);
        System.out.println("After update:\n" + daoAdresse.get(res));
        daoAdresse.delete(res);
        System.out.println("After delete:");
        daoAdresse.get().forEach(System.out::println);
    }

    private static void client() throws SQLException {
        DaoClient daoClient = DaoClient.getInstance();
        DaoAdresse daoAdresse = DaoAdresse.getInstance();
        System.out.println("Client:");
        daoClient.get().forEach(System.out::println);
        int res = daoClient.add(new Client(
                "Paul",
                10.50f,
                0,
                daoAdresse.get(1)
        ));
        Client client = daoClient.get(res);
        System.out.println("After add:\n" + client);
        client
                .setSold(8.25f)
                .setFidelity(1);
        daoClient.update(client);
        System.out.println("After update:\n" + daoClient.get(res));
        daoClient.delete(res);
        System.out.println("After delete");
        daoClient.get().forEach(System.out::println);
    }

    private static void livreur() throws SQLException {
        DaoLivreur daoLivreur = DaoLivreur.getInstance();
        System.out.println("Livreur:");
        daoLivreur.get().forEach(System.out::println);
        int res = daoLivreur.add(new Livreur("John"));
        Livreur livreur = daoLivreur.get(res);
        System.out.println("After add:\n" + livreur);
        livreur.setName("Paul");
        daoLivreur.update(livreur);
        System.out.println("After update:\n" + daoLivreur.get(res));
        daoLivreur.delete(res);
        System.out.println("After delete");
        daoLivreur.get().forEach(System.out::println);
    }

    private static void typeVehicule() throws SQLException {
        DaoTypeVehicule daoTypeVehicule = DaoTypeVehicule.getInstance();
        System.out.println("TypeVehicule:");
        daoTypeVehicule.get().forEach(System.out::println);
        int res = daoTypeVehicule.add(new TypeVehicule("Scooter des mers"));
        TypeVehicule typeVehicule = daoTypeVehicule.get(res);
        System.out.println("After add:\n" + typeVehicule);
        typeVehicule.setName("Drone");
        daoTypeVehicule.update(typeVehicule);
        System.out.println("After update:\n" + daoTypeVehicule.get(res));
        daoTypeVehicule.delete(res);
        System.out.println("After delete");
        daoTypeVehicule.get().forEach(System.out::println);
    }

    private static void taille() throws SQLException {
        DaoTaille daoTaille = DaoTaille.getInstance();
        System.out.println("Taille:");
        daoTaille.get().forEach(System.out::println);
        int res = daoTaille.add(new Taille("Senior", 2.0f));
        Taille taille = daoTaille.get(res);
        System.out.println("After add:\n" + taille);
        taille.setName("Incroyable");
        daoTaille.update(taille);
        System.out.println("After update:\n" + daoTaille.get(res));
        daoTaille.delete(res);
        System.out.println("After delete");
        daoTaille.get().forEach(System.out::println);
    }

    private static void vehicule() throws SQLException {
        DaoVehicule daoVehicule = DaoVehicule.getInstance();
        DaoTypeVehicule daoTypeVehicule = DaoTypeVehicule.getInstance();
        System.out.println("Vehicule:");
        daoVehicule.get().forEach(System.out::println);
        int res = daoVehicule.add(new Vehicule(
                "AB-000-CD",
                daoTypeVehicule.get(1)
        ));
        Vehicule vehicule = daoVehicule.get(res);
        System.out.println("After add:\n" + vehicule);
        vehicule.setImmatriculation("AB-001-CD");
        daoVehicule.update(vehicule);
        System.out.println("After update:\n" + daoVehicule.get(res));
        daoVehicule.delete(res);
        System.out.println("After delete");
        daoVehicule.get().forEach(System.out::println);
    }

    private static void ingredient() throws SQLException {
        DaoIngredient daoIngredient = DaoIngredient.getInstance();
        System.out.println("Ingredient:");
        daoIngredient.get().forEach(System.out::println);
        int res = daoIngredient.add(new Ingredient("Ingredient"));
        Ingredient ingredient = daoIngredient.get(res);
        System.out.println("After add:\n" + ingredient);
        ingredient.setName("Ingredient updated");
        daoIngredient.update(ingredient);
        System.out.println("After update:\n" + daoIngredient.get(res));
        daoIngredient.delete(res);
        System.out.println("After delete");
        daoIngredient.get().forEach(System.out::println);
        System.out.println("getPizzas():");
        daoIngredient.get(1).getPizzas().forEach(System.out::println);
    }

    private static void pizza() throws SQLException {
        DaoPizza daoPizza = DaoPizza.getInstance();
        System.out.println("Pizza:");
        daoPizza.get().forEach(System.out::println);
        int res = daoPizza.add(new Pizza(
                "Special du chef",
                12.0f
        ));
        Pizza pizza = daoPizza.get(res);
        System.out.println("After add:\n" + pizza);
        pizza.setName("Pizza updated");
        daoPizza.update(pizza);
        System.out.println("After update:\n" + daoPizza.get(res));
        daoPizza.delete(res);
        System.out.println("After delete");
        daoPizza.get().forEach(System.out::println);
        System.out.println("getIngredients():");
        daoPizza.get(2).getIngredients().forEach(System.out::println);
    }

    private static void commande() throws SQLException {
        DaoCommande daoCommande = DaoCommande.getInstance();
        DaoClient daoClient = DaoClient.getInstance();
        DaoPizza daoPizza = DaoPizza.getInstance();
        DaoTaille daoTaille = DaoTaille.getInstance();
        DaoLivreur daoLivreur = DaoLivreur.getInstance();
        DaoVehicule daoVehicule = DaoVehicule.getInstance();
        System.out.println("Commande:");
        daoCommande.get().forEach(System.out::println);
        int res = daoCommande.add(new Commande(
                new Date(System.currentTimeMillis() - (900 * 1000)),
                null,
                daoClient.get(1),
                daoPizza.get(1),
                daoTaille.get(1),
                daoLivreur.get(1),
                daoVehicule.get(1)
        ));
        Commande commande = daoCommande.get(res);
        System.out.println("After add:\n" + commande);
        commande.setDateDelivery(new Date());
        daoCommande.update(commande);
        System.out.println("After update:\n" + commande);
        daoCommande.delete(res);
        System.out.println("After delete:");
        daoCommande.get().forEach(System.out::println);
    }

    private static void special() throws SQLException {
        DaoSpecial daoSpecial = DaoSpecial.getInstance();
        System.out.println("Delivery ticket:");
        daoSpecial.getDeliveryTicket().forEach(System.out::println);
        System.out.println("Unused vehicules:");
        daoSpecial.getUnusedVehicule().forEach(System.out::println);
        System.out.println("Command by client:");
        daoSpecial.getCommandCountByClient().forEach((k, v) -> System.out.println(String.format("%s:%d", k, v)));
        System.out.println("Average command by client:\n" + daoSpecial.AverageCommandCount());
        System.out.println("Most loyal clients");
        daoSpecial.mostLoyalClients().forEach(System.out::println);
        System.out.println("Menu:");
        daoSpecial.getMenu().forEach((k, v) -> {
            System.out.println(k);
            v.forEach(ingredient -> System.out.println("\t" + ingredient));
        });
    }

    public static void displaySQLException(SQLException e) {
        System.err.print(e.getSQLState());
        System.err.print(" " + e.getMessage());
        System.err.println(" (" + e.getErrorCode() + ")");
        e.printStackTrace();
    }
}
