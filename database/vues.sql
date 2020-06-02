CREATE VIEW V_TicketLivraison (id, nomLivreur, nomTypeVehicule, plaqueImmat, nomClient, dateCommande, retard, nomPizza, prix) AS (
    SELECT idCommande, nomLivreur, nomTypeVehicule, plaqueImmat, nomClient, dateCommande, TIMEDIFF(dateLivraison, dateCommande) AS retard, nomPizza, (prix * ratioPrix) AS prix
    FROM livreur l, vehicule v, TypeVehicule tv, client clt, commande cmd, pizza p, taille t
    WHERE cmd.idLivreur = l.idLivreur
      AND cmd.idVehicule = v.idVehicule
      AND v.idTypeVehicule = tv.idTypeVehicule
      AND cmd.idClient = clt.idClient
      AND cmd.idPizza = p.idPizza
      AND cmd.idTaille = t.idTaille
);
