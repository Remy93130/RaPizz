CREATE DATABASE IF NOT EXISTS RaPizz;

USE RaPizz;

CREATE TABLE Pizza(
   idPizza INT AUTO_INCREMENT,
   nomPizza VARCHAR(50) NOT NULL,
   prix DECIMAL(4,2) NOT NULL,
   PRIMARY KEY(idPizza)
) ENGINE=InnoDB;

CREATE TABLE Ingredient(
   idIngredient INT AUTO_INCREMENT,
   nomIngredient VARCHAR(50) NOT NULL,
   PRIMARY KEY(idIngredient)
) ENGINE=InnoDB;

CREATE TABLE TypeVehicule(
   idTypeVehicule INT AUTO_INCREMENT,
   nomTypeVehicule VARCHAR(50) NOT NULL,
   PRIMARY KEY(idTypeVehicule)
) ENGINE=InnoDB;

CREATE TABLE Livreur(
   idLivreur INT AUTO_INCREMENT,
   nomLivreur VARCHAR(50) NOT NULL,
   PRIMARY KEY(idLivreur)
) ENGINE=InnoDB;

CREATE TABLE Adresse(
   idAdresse INT AUTO_INCREMENT,
   ville VARCHAR(50) NOT NULL,
   rue VARCHAR(50) NOT NULL,
   numero INT NOT NULL,
   codePostal VARCHAR(50) NOT NULL,
   PRIMARY KEY(idAdresse)
) ENGINE=InnoDB;

CREATE TABLE Taille(
   idTaille VARCHAR(50),
   ratioPrix DECIMAL(3,2) NOT NULL,
   PRIMARY KEY(idTaille)
) ENGINE=InnoDB;

CREATE TABLE Vehicule(
   idVehicule CHAR(9),
   idTypeVehicule INT NOT NULL,
   PRIMARY KEY(idVehicule),
   FOREIGN KEY(idTypeVehicule) REFERENCES TypeVehicule(idTypeVehicule)
) ENGINE=InnoDB;

CREATE TABLE Client(
   idClient INT AUTO_INCREMENT,
   nomClient VARCHAR(50) NOT NULL,
   solde DECIMAL(6,2) NOT NULL DEFAULT 0,
   pointFidelite INT NOT NULL DEFAULT 0,
   idAdresse INT NOT NULL,
   PRIMARY KEY(idClient),
   FOREIGN KEY(idAdresse) REFERENCES Adresse(idAdresse)
) ENGINE=InnoDB;

CREATE TABLE Commande(
   idCommande INT AUTO_INCREMENT,
   dateCommande DATETIME NOT NULL,
   dateLivraison DATETIME,
   idClient INT NOT NULL,
   idPizza INT NOT NULL,
   idTaille VARCHAR(50) NOT NULL,
   idLivreur INT NOT NULL,
   idVehicule CHAR(9) NOT NULL,
   PRIMARY KEY(idCommande),
   FOREIGN KEY(idClient) REFERENCES Client(idClient),
   FOREIGN KEY(idPizza) REFERENCES Pizza(idPizza),
   FOREIGN KEY(idTaille) REFERENCES Taille(idTaille),
   FOREIGN KEY(idLivreur) REFERENCES Livreur(idLivreur),
   FOREIGN KEY(idVehicule) REFERENCES Vehicule(idVehicule)
) ENGINE=InnoDB;

CREATE TABLE Contient(
   idPizza INT NOT NULL,
   idIngredient INT NOT NULL,
   PRIMARY KEY(idPizza, idIngredient),
   FOREIGN KEY(idPizza) REFERENCES Pizza(idPizza),
   FOREIGN KEY(idIngredient) REFERENCES Ingredient(idIngredient)
) ENGINE=InnoDB;
