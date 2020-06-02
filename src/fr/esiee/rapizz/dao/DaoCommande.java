package fr.esiee.rapizz.dao;

import fr.esiee.rapizz.dao.interfaces.DaoCommandeInterface;
import fr.esiee.rapizz.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaoCommande extends AbstactDao implements DaoCommandeInterface {
    private static DaoCommande instance = null;

    public static DaoCommande getInstance() {
        if (instance == null) {
            instance = new DaoCommande();
        }
        return instance;
    }

    private DaoCommande() {
        super();
    }

    @Override
    public int add(Commande commande) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(this.parseDate(commande.getDateCommand()));
        params.add(this.parseDate(commande.getDateDelivery()));
        params.add(String.valueOf(commande.getClient().getId()));
        params.add(String.valueOf(commande.getPizza().getId()));
        params.add(String.valueOf(commande.getTaille().getId()));
        params.add(String.valueOf(commande.getLivreur().getId()));
        params.add(String.valueOf(commande.getVehicule().getId()));
        String sql = "INSERT INTO Commande (dateCommande, dateLivraison, idClient, idPizza, idTaille, " +
                "idLivreur, idVehicule) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return this.db.preparedRequestInsert(sql, params);
    }

    @Override
    public Commande get(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        String sql = "SELECT * FROM Commande " +
                "NATURAL JOIN Client " +
                "NATURAL JOIN Pizza " +
                "NATURAL JOIN Taille " +
                "NATURAL JOIN Livreur " +
                "NATURAL JOIN Vehicule " +
                "NATURAL JOIN TypeVehicule " +
                "NATURAL JOIN Adresse " +
                "WHERE idCommande=?";
        ResultSet resultSet = this.db.preparedRequestGet(sql, params);
        if (resultSet.next()) {
            return this.createCommande(resultSet);
        }
        return null;
    }

    @Override
    public List<Commande> get() throws SQLException {
        ArrayList<Commande> commandes = new ArrayList<>();
        String sql = "SELECT * FROM Commande " +
                "NATURAL JOIN Client " +
                "NATURAL JOIN Pizza " +
                "NATURAL JOIN Taille " +
                "NATURAL JOIN Livreur " +
                "NATURAL JOIN Vehicule " +
                "NATURAL JOIN TypeVehicule " +
                "NATURAL JOIN Adresse";
        ResultSet resultSet = this.db.request(sql);
        while (resultSet.next()) {
            commandes.add(createCommande(resultSet));
        }
        return commandes;
    }

    @Override
    public boolean update(Commande commande) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(this.parseDate(commande.getDateCommand()));
        params.add(this.parseDate(commande.getDateDelivery()));
        params.add(String.valueOf(commande.getClient().getId()));
        params.add(String.valueOf(commande.getPizza().getId()));
        params.add(String.valueOf(commande.getTaille().getId()));
        params.add(String.valueOf(commande.getLivreur().getId()));
        params.add(String.valueOf(commande.getVehicule().getId()));
        params.add(String.valueOf(commande.getId()));
        String sql = "UPDATE Commande SET dateCommande=?, dateLivraison=?, idClient=?, idPizza=?, idTaille=?, " +
                "idLivreur=?, idVehicule=? WHERE idCommande=?";
        return this.db.preparedRequestUpdate(sql, params);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        return this.db.preparedRequestUpdate("DELETE FROM Commande WHERE idCommande=?", params);
    }

    private String parseDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = null;
        try {
            formattedDate = df.format(date);

        } catch (Exception ignored) {
        }
        return formattedDate;
    }

    private Date parseDate(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date formattedDate = null;
        try {
            formattedDate = df.parse(date);
        } catch (Exception ignored) {
        }
        return formattedDate;
    }

    private Commande createCommande(ResultSet data) throws SQLException {
        return new Commande(
                data.getInt("idCommande"),
                this.parseDate(data.getString("dateCommande")),
                this.parseDate(data.getString("dateLivraison")),
                new Client(
                        data.getInt("idClient"),
                        data.getString("nomClient"),
                        data.getFloat("solde"),
                        data.getInt("pointFidelite"),
                        new Adresse(
                                data.getInt("idAdresse"),
                                data.getString("ville"),
                                data.getString("rue"),
                                data.getInt("numero"),
                                data.getString("codePostal")
                        )
                ),
                new Pizza(
                        data.getInt("idPizza"),
                        data.getString("nomPizza"),
                        data.getFloat("prix")
                ),
                new Taille(
                        data.getInt("idTaille"),
                        data.getString("nomTaille"),
                        data.getFloat("ratioPrix")
                ),
                new Livreur(
                        data.getInt("idLivreur"),
                        data.getString("nomLivreur")
                ),
                new Vehicule(
                        data.getInt("idVehicule"),
                        data.getString("plaqueImmat"),
                        new TypeVehicule(
                                data.getInt("idTypeVehicule"),
                                data.getString("nomTypeVehicule")
                        )
                )
        );
    }
}
