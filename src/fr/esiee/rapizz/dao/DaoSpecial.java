package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.model.DeliveryTicket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoSpecial extends AbstactDao {
    private static DaoSpecial instance = null;

    public static DaoSpecial getInstance() {
        if (instance == null) {
            instance = new DaoSpecial();
        }
        return instance;
    }

    private DaoSpecial() {
        super();
    }

    public List<DeliveryTicket> getDeliveryTicket() throws SQLException {
        ArrayList<DeliveryTicket> deliveryTickets = new ArrayList<>();
        String sql = "SELECT idCommande, nomLivreur, nomTypeVehicule, plaqueImmat, nomClient, dateCommande, " +
                "TIMEDIFF(dateLivraison, dateCommande) AS retard, nomPizza, (prix * ratioPrix) as prix " +
                "FROM livreur l, vehicule v, TypeVehicule tv, client clt, commande cmd, pizza p, taille t " +
                "WHERE cmd.idLivreur = l.idLivreur AND cmd.idVehicule = v.idVehicule " +
                "AND v.idTypeVehicule = tv.idTypeVehicule AND cmd.idClient = clt.idClient " +
                "AND cmd.idPizza = p.idPizza AND cmd.idTaille = t.idTaille";
        ResultSet resultSet = this.db.request(sql);
        while (resultSet.next()) {
            deliveryTickets.add(new DeliveryTicket(
                    resultSet.getInt("idCommande"),
                    resultSet.getString("nomLivreur"),
                    resultSet.getString("plaqueImmat"),
                    resultSet.getString("nomClient"),
                    resultSet.getDate("retard"),
                    resultSet.getString("nomPizza"),
                    resultSet.getFloat("prix")
            ));
        }
        return deliveryTickets;
    }

    public List<String> getUnusedVehicule() throws SQLException {
        List<String> vehicules = new ArrayList<>();
        String sql = "SELECT v.idVehicule, v.plaqueImmat FROM vehicule v WHERE v.idVehicule " +
                "NOT IN (SELECT c.idVehicule FROM commande c)";
        ResultSet resultSet = this.db.request(sql);
        while (resultSet.next()) {
            vehicules.add(resultSet.getString("plaqueImmat"));
        }
        return vehicules;
    }

    public Map<String, Integer> getCommandCountByClient() throws SQLException {
        HashMap<String, Integer> clients = new HashMap<>();
        String sql = "SELECT clt.idClient, nomClient, COUNT(*) AS nbCommande FROM client clt " +
                "NATURAL JOIN commande GROUP BY clt.idClient";
        ResultSet resultSet = this.db.request(sql);
        while (resultSet.next()) {
            clients.put(
                    resultSet.getString("nomClient"),
                    resultSet.getInt("nbCommande")
            );
        }
        return clients;
    }

    public float AverageCommandCount() throws SQLException {
        String sql = "SELECT AVG(nbCommande) FROM (SELECT clt.idClient, nomClient, COUNT(*) AS nbCommande FROM " +
                "client clt NATURAL JOIN commande GROUP BY clt.idClient) T";
        ResultSet resultSet = this.db.request(sql);
        if (resultSet.next()) {
            return resultSet.getFloat(1);
        }
        return -1f;
    }

    public List<String> mostLoyalClients() throws SQLException {
        ArrayList<String> clients = new ArrayList<>();
        String sql = "SELECT T1.idClient, T1.nomClient FROM (SELECT clt1.idClient, clt1.nomClient, COUNT(*) AS " +
                "nbCommande FROM client clt1 NATURAL JOIN commande GROUP BY clt1.idClient) T1 WHERE T1.nbCommande > " +
                "(SELECT AVG(T2.nbCommande) FROM (SELECT clt2.idClient, clt2.nomClient, COUNT(*) AS nbCommande FROM " +
                "client clt2 NATURAL JOIN commande GROUP BY clt2.idClient)T2)";
        ResultSet resultSet = this.db.request(sql);
        while (resultSet.next()) {
            clients.add(resultSet.getString("nomClient"));
        }
        return clients;
    }
}
