package fr.esiee.rapizz.main;

import fr.esiee.rapizz.util.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        try {
            Connection con = Database.getConnection();
            con.close();
            System.out.println("Connection open & closed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
