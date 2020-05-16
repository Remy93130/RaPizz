package fr.esiee.rapizz.main;

import fr.esiee.rapizz.util.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        try {
            Connection con = Database.getConnection();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
