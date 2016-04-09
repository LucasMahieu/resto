-- Traduction des entités normales :
CREATE TABLE Tables (
	numeroTable INTEGER check (numeroTable > 0),
	nombrePlaceIsolee INTEGER check (nombrePlaceIsolee > 0),
	nombrePlaceAccolee1 INTEGER check (nombrePlaceAccolee1 > 0),
	nombrePlaceAccolee2 INTEGER check (nombrePlaceAccolee2 > 0),
	localisation VARCHAR(20),
    numeroGroupe INTEGER check (numeroGroupe > 0),
	CONSTRAINT KTables PRIMARY KEY (numeroTable)
);

CREATE TABLE Client (
	numeroClient INTEGER CONSTRAINT KnumeroClient PRIMARY KEY check (numeroClient > 0),
	nomClient VARCHAR(20),
	telephoneClient VARCHAR(20)
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

CREATE TABLE Service (
	typeService VARCHAR(20),
	dateService VARCHAR(20),
    -- Attributs lié à la cardinalité 1..1
    nomCarte VARCHAR(20) REFERENCES Carte(nomCarte),
	CONSTRAINT KService PRIMARY KEY (typeService, dateService)
);

CREATE TABLE Reservation (
	numeroReservation INTEGER check (numeroReservation > 0),
	nbPersonnes INTEGER check (nbPersonnes > 0),
	prixTotal INTEGER check (prixTotal > 0),
    -- Attributs liés aux cardinalités 1..1
    numeroClient INTEGER REFERENCES Client(numeroClient),
	typeService VARCHAR(20) REFERENCES Service(typeService),
	dateService VARCHAR(20) REFERENCES Service(dateService),
	CONSTRAINT KReservation PRIMARY KEY (numeroReservation)
);

-- Traduction des sous-types d'entité
CREATE TABLE Menu (
	nomMenu VARCHAR(20) CONSTRAINT KnomMenu PRIMARY KEY REFERENCES Article(nomArticle)
);

CREATE TABLE Choix (
	nomChoix VARCHAR(20) CONSTRAINT KnomChoix PRIMARY KEY REFERENCES Article(nomArticle)
);

CREATE TABLE Plat (
	nomPlat VARCHAR(20) CONSTRAINT KnomPlat PRIMARY KEY REFERENCES Choix(nomChoix)
);

CREATE TABLE Entree (
	nomEntree VARCHAR(20) CONSTRAINT KnomEntree PRIMARY KEY REFERENCES Choix(nomChoix)
);

CREATE TABLE Dessert (
	nomDessert VARCHAR(20) CONSTRAINT KnomDessert PRIMARY KEY REFERENCES Choix(nomChoix)
);

CREATE TABLE Boisson (
	nomBoisson VARCHAR(20) CONSTRAINT KnomBoisson PRIMARY KEY REFERENCES Choix(nomChoix)
);

-- Traduction des entités faibles : aucunes
-- Traduction des multiplicités 1..1 : OK (voir ci dessus)
-- Traduction des multiplicités 0..1
CREATE TABLE estReservee (
    numeroTable INTEGER REFERENCES Tables(numeroTable),
    numeroReservation INTEGER REFERENCES Reservation(numeroReservation),
	CONSTRAINT KestReservee PRIMARY KEY (numeroTable)
);

-- Traduction des multiplicités ?..* (et 0..2)
CREATE TABLE sontCommandes (
	nomArticle VARCHAR(20) REFERENCES Article(nomArticle),
	numeroReservation INTEGER REFERENCES Reservation(numeroReservation),
	CONSTRAINT KsontCommandes PRIMARY KEY (nomArticle, numeroReservation)
);

CREATE TABLE Disponibles (
	nomArticle VARCHAR(20) REFERENCES Article(nomArticle),
    nomCarte VARCHAR(20) REFERENCES Carte(nomCarte),
	CONSTRAINT KDisponibles PRIMARY KEY (nomArticle, nomCarte)
);

CREATE TABLE estBase (
	nomMenu VARCHAR(20) REFERENCES Menu(nomMenu),
	nomPlat VARCHAR(20) REFERENCES Plat(nomPlat),
	CONSTRAINT KestBase PRIMARY KEY (nomMenu, nomPlat)
);

CREATE TABLE estCompose (
	nomMenu VARCHAR(20) REFERENCES Menu(nomMenu),
	nomChoix VARCHAR(20) REFERENCES Choix(nomChoix),
	CONSTRAINT KestCompose PRIMARY KEY (nomMenu, nomChoix)
);

CREATE TABLE sontVoisines (
    numeroTable1 INTEGER REFERENCES Tables(numeroTable),
    numeroTable2 INTEGER REFERENCES Tables(numeroTable),
	CONSTRAINT KsontVoisines PRIMARY KEY (numeroTable1, numeroTable2)
);
