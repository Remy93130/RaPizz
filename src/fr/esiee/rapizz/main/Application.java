package fr.esiee.rapizz.main;

import fr.esiee.rapizz.dao.DaoAdresse;
import fr.esiee.rapizz.model.Adresse;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        try {
            Application.adresse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void adresse() throws SQLException {
        DaoAdresse daoAdresse = new DaoAdresse();
        System.out.println("Adresse:");
        daoAdresse.get().forEach(System.out::println);
        int res = daoAdresse.add(new Adresse(
                -1,
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
}
