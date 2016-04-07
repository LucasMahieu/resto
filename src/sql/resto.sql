CREATE TABLE Tables (
	numeroTable INTEGER CONSTRAINT KnumeroTable PRIMARY KEY check (numeroTable > 0),
	nombrePlaceIsolee INTEGER check (nombrePlaceIsolee > 0),
	nombrePlaceAccolee1 INTEGER check (nombrePlaceAccolee1 > 0),
	nombrePlaceAccolee2 INTEGER check (nombrePlaceAccolee2 > 0),
	localisation VARCHAR(20),
	numeroGroupe INTEGER check (numeroGroupe > 0)
);

CREATE TABLE Client (
	numeroClient INTEGER CONSTRAINT KnumeroCLIENT PRIMARY KEY check (numeroClient > 0),
	nomClient VARCHAR(20),
	telephoneClient VARCHAR(20)
);

CREATE TABLE Reservation (
	numeroReservation INTEGER check (numeroReservation > 0),
    numeroClient INTEGER REFERENCES Client,
	nbPersonnes INTEGER check (nbPersonnes > 0),
	prixTotal INTEGER check (prixTotal > 0)
	CONSTRAINT KReservation PRIMARY KEY (numeroReservation, numeroClient)
);

CREATE TABLE aReserve (
	numeroReservation INTEGER REFERENCES Reservation.numeroReservation,
    numeroClient INTEGER REFERENCES Client.numeroClient,
	nbPersonnes INTEGER REFERENCES Reservation.nbPersonnes,
	prixTotal INTEGER REFERENCES Reservation.prixTotal,
	nomClient VARCHAR(20),
	telephoneClient VARCHAR(20)
	CONSTRAINT KReservation PRIMARY KEY (numeroReservation, numeroClient)
);

CREATE TABLE Service (
	typeService VARCHAR(20),
	dateService VARCHAR(20),
	CONSTRAINT KService PRIMARY KEY (typeService, dateService)
);

CREATE TABLE Carte (
    nomCarte VARCHAR(20) CONSTRAINT KnomCarte PRIMARY KEY
);

CREATE TABLE Article (
	nomArticle VARCHAR(20) CONSTRAINT KnomArticle PRIMARY KEY,
	prixArticle REAL check (prixArticle > 0),
	specialite VARCHAR(20),
	quantiteArticle INTEGER check (quantiteArticle > 0)
);

CREATE TABLE Menu (
	nomMenu VARCHAR(20) CONSTRAINT KnomMenu PRIMARY KEY REFERENCES Article
);

CREATE TABLE Choix (
	nomChoix VARCHAR(20) CONSTRAINT KnomChoix PRIMARY KEY REFERENCES Article
);

CREATE TABLE Plat (
	nomPlat VARCHAR(20) CONSTRAINT KnomPlat PRIMARY KEY REFERENCES Choix
);

CREATE TABLE Entree (
	nomEntree VARCHAR(20) CONSTRAINT KnomEntree PRIMARY KEY REFERENCES Choix
);

CREATE TABLE Dessert (
	nomDessert VARCHAR(20) CONSTRAINT KnomDessert PRIMARY KEY REFERENCES Choix
);

CREATE TABLE Boisson (
	nomBoisson VARCHAR(20) CONSTRAINT KnomBoisson PRIMARY KEY REFERENCES Choix
);

