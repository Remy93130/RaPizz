INSERT INTO Adresse (idAdresse, ville, rue, numero, codePostal)
VALUES (1, 'Nogent-sur-Marne', 'Grande rue Charles de Gaulle', 102, '94130'),
       (2, 'Emerainville', 'Rue de l\'orangerie', 4, '77184'),
       (3, 'Noisy-Le-Grand', 'Rue des mastraits', 25, '93160'),
       (4, 'Noisy-Le-Grand', 'boulevard Blaise Pascal', 2, '93160'),
       (5, 'Perreux-Sur-Marne', 'Avenue Gabriel Peri', 54, '94170'),
       (6, 'Noisy-Le-Grand', 'Clos des Cascades', 43, '93160');


INSERT INTO Client (idClient, nomClient, solde, pointFidelite, idAdresse)
VALUES (1, 'JOUVE', '15.00', 0, 2),
       (2, 'BUCZKO', '20.00', 0, 1),
       (3, 'BARBERET', '100.00', 2, 3),
       (4, 'DE GAULLE', '1000.00', 3, 4),
       (5, 'CAVALIER', '20.00', 1, 6),
       (6, 'CHARDON', '20.00', 0, 5),
       (7, 'LEVACQUE', '30.00', 4, 5);


INSERT INTO Ingredient (idIngredient, nomIngredient)
VALUES (1, 'Gruyere'),
       (2, 'Cheddar'),
       (3, 'Brie'),
       (4, 'Emmental'),
       (5, 'Chevre'),
       (6, 'Comte'),
       (7, 'Mozarella'),
       (8, 'Tomate'),
       (9, 'Olive'),
       (10, 'Champignon'),
       (11, 'Jambon de Parme'),
       (12, 'Bacon'),
       (13, 'Lardon'),
       (14, 'Creme fraiche'),
       (15, 'Patate'),
       (16, 'Oeuf'),
       (17, 'Bleu'),
       (18, 'Oignon'),
       (19, 'Viande hachee de boeuf'),
       (20, 'Sauce barbecue'),
       (21, 'Merguez'),
       (22, 'Chorizo'),
       (23, 'Sauce piquante'),
       (24, 'Fromage raclette'),
       (25, 'Poivron'),
       (26, 'Poulet'),
       (27, 'Curry'),
       (28, 'Salade'),
       (29, 'Saumon'),
       (30, 'Viande kebab'),
       (31, 'Jambon');


INSERT INTO Livreur (idLivreur, nomLivreur)
VALUES (1, 'Leo'),
       (2, 'Christine'),
       (3, 'Melissa');


INSERT INTO Pizza (idPizza, nomPizza, prix)
VALUES (1, 'Margarita', '8.00'),
       (2, '4 Fromages', '10.00'),
       (3, 'Savoyarde', '11.00'),
       (4, 'Cannibale', '10.50'),
       (5, 'Saumon', '10.00'),
       (6, 'Vegan', '9.00'),
       (7, 'Reine', '9.00'),
       (8, 'Chef', '10.00'),
       (9, 'Orientale', '10.00'),
       (10, '8 Fromages', '12.00');


INSERT INTO Taille (nomTaille, ratioPrix)
VALUES ('Humaine', '1.00'),
       ('Naine', '0.66'),
       ('Ogresse', '1.33');


INSERT INTO TypeVehicule (idTypeVehicule, nomTypeVehicule)
VALUES (1, 'Voiture'),
       (2, 'Moto');


INSERT INTO Vehicule (plaqueImmat, idTypeVehicule)
VALUES ('00-000-01', 1),
       ('00-000-02', 1),
       ('00-000-03', 2),
       ('00-000-04', 2);


INSERT INTO Contient (idPizza, idIngredient)
VALUES (1, 4),
       (2, 8),
       (2, 1),
       (2, 4),
       (2, 7),
       (2, 17),
       (3, 4),
       (3, 13),
       (3, 14),
       (3, 15),
       (4, 2),
       (4, 20),
       (4, 21),
       (4, 22),
       (4, 26),
       (5, 4),
       (5, 14),
       (5, 29),
       (6, 8),
       (6, 10),
       (6, 25),
       (6, 28),
       (7, 1),
       (7, 9),
       (7, 11),
       (8, 4),
       (8, 14),
       (8, 18),
       (8, 19),
       (9, 4),
       (9, 16),
       (9, 21),
       (9, 22),
       (9, 25),
       (10, 1),
       (10, 2),
       (10, 3),
       (10, 4),
       (10, 5),
       (10, 7),
       (10, 17),
       (10, 24);


INSERT INTO Commande (idCommande, dateCommande, dateLivraison, idClient, idPizza, idTaille, idLivreur, idVehicule)
VALUES (1, '2020-05-16 16:00:01', '2020-05-16 16:40:01', 3, 10, 1, 2, 1),
       (2, '2020-05-16 16:20:02', '2020-05-16 16:28:02', 2, 6, 3, 3, 2),
       (3, '2020-05-16 19:20:10', '2020-05-16 19:30:10', 5, 8, 2, 1, 3),
       (4, '2020-05-16 20:34:10', '2020-05-16 20:50:43', 4, 10, 3, 3, 1),
       (5, '2020-05-16 20:38:10', '2020-05-16 20:58:10', 7, 4, 1, 1, 2),
       (6, '2020-05-16 21:18:10', '2020-05-16 21:25:10', 1, 3, 2, 1, 1),
       (7, '2020-05-18 20:38:10', '2020-05-18 20:50:10', 3, 5, 3, 2, 2),
       (8, '2020-05-20 20:50:10', '2020-05-20 21:00:10', 5, 6, 1, 1, 1),
       (9, '2020-05-21 19:50:10', '2020-05-21 19:59:10', 4, 8, 3, 3, 1),
       (10,'2020-05-21 22:00:02', '2020-05-21 22:35:02', 6, 8, 1, 3, 1);
