# Menu
SELECT nomPizza, prix, nomIngredient
FROM pizza p, ingredient i, contient c
WHERE p.idPizza = c.idPizza
  AND i.idIngredient = c.idIngredient
ORDER BY nomPizza, nomIngredient;


# Fiche de livraison
SELECT idCommande, nomLivreur, nomTypeVehicule, plaqueImmat, nomClient, dateCommande, TIMEDIFF(dateLivraison, dateCommande) AS retard, nomPizza, (prix * ratioPrix) as prix
FROM livreur l, vehicule v, TypeVehicule tv, client clt, commande cmd, pizza p, taille t
WHERE cmd.idLivreur = l.idLivreur
  AND cmd.idVehicule = v.idVehicule
  AND v.idTypeVehicule = tv.idTypeVehicule
  AND cmd.idClient = clt.idClient
  AND cmd.idPizza = p.idPizza
  AND cmd.idTaille = t.idTaille


# Vehicules non utilises
SELECT v.idVehicule, v.plaqueImmat
FROM vehicule v
WHERE v.idVehicule NOT IN (
  SELECT c.idVehicule
  FROM commande c
);


# Nombre de commande par client
SELECT clt.idClient, nomClient, COUNT(*) AS nbCommande
FROM client clt
NATURAL JOIN commande
GROUP BY clt.idClient;


# Moyenne de commande
SELECT AVG(nbCommande)
FROM (
  SELECT clt.idClient, nomClient, COUNT(*) AS nbCommande
  FROM client clt
  NATURAL JOIN commande
  GROUP BY clt.idClient
) T;


# Client ayant commande plus que la moyenne
SELECT T1.idClient, T1.nomClient
FROM (
  SELECT clt1.idClient, clt1.nomClient, COUNT(*) AS nbCommande
  FROM client clt1
  NATURAL JOIN commande
  GROUP BY clt1.idClient
) T1
WHERE T1.nbCommande > (
  SELECT AVG(T2.nbCommande)
  FROM (
    SELECT clt2.idClient, clt2.nomClient, COUNT(*) AS nbCommande
    FROM client clt2
    NATURAL JOIN commande
    GROUP BY clt2.idClient
  ) T2
);
