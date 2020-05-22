package fr.esiee.rapizz.main;

import fr.esiee.rapizz.dao.DaoAdresse;
import fr.esiee.rapizz.dao.DaoClient;
import fr.esiee.rapizz.model.Adresse;
import fr.esiee.rapizz.model.Client;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        try {
            // Application.adresse();
            Application.client();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void adresse() throws SQLException {
        DaoAdresse daoAdresse = new DaoAdresse();
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
        DaoClient daoClient = new DaoClient();
        DaoAdresse daoAdresse = new DaoAdresse();
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
}
