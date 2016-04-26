-- TABLES
insert into Tables
	Values(1,4,3,2,'entree');
insert into Tables
	Values(2,3,1,1,'cheminee');
insert into Tables
	Values(3,5,4,2,'entree');
insert into Tables
	Values(4,8,6,5,'entree');
insert into Tables
	Values(5,3,2,1,'cheminee');

-- CLIENTS
insert into Client
	Values(1,'Johnny', '0601010101');
insert into Client
	Values(2,'Francine', '0601022222');
insert into Client
	Values(3,'Richard', '0601012222');
	
-- CARTES
insert into Carte
	Values('carte repas');
insert into Carte
	Values('carte bar');
insert into Carte
	Values('carte mer');
insert into Carte
	Values('carte terre');
	
-- ARTICLES
insert into Article
	Values('couscous', 15, 'arabe');
insert into Article
	Values('gateaux maroc', 8, 'arabe');
insert into Article
	Values('salade tunisienne', 8, 'arabe');
insert into Article
	Values('the', 3, 'arabe');

insert into Article
	Values('pates bolo', 10, 'italien');
insert into Article
	Values('tiramisu', 7, 'italien');
insert into Article
	Values('salade de tomates', 8, 'italien');
insert into Article
	Values('glace', 5, 'italien');
insert into Article
	Values('jus orange', 8, 'italien');

insert into Article
	Values('salade cesar', 8, 'americain');
insert into Article
	Values('steak frites', 10, 'americain');
insert into Article
	Values('coca', 3, 'americain');
insert into Article
	Values('sunday', 10, 'americain');
	
insert into Article
	Values('carpaccio', 12, 'français');
insert into Article
	Values('champagne', 25, 'français');
insert into Article
	Values('poulet', 16, 'français');
insert into Article
	Values('fraise', 16, 'français');
insert into Article
	Values('perrier', 3, 'français');
INSERT INTO  Article
VALUES ('canard', 16, 'français');
INSERT INTO Article
VALUES ('pâté',12, 'français');
Insert INTO Article
VALUES ('saumon', 15, 'français');
Insert INTO Article
VALUES ('foie gras', 12, 'français');
INSERT INTO Article
VALUES ('saint jacques', 13, 'français');
INSERT INTO Article
VALUES ('burger',8,'americain');
INSERT INTO Article
VALUES ('pizza',9,'italien');
INSERT INTO Article
VALUES ('fondant chocolat',5,'français');
INSERT INTO Article
Values ('cognac',10,'français');

insert into Article
	Values('menu enfant', 12, NULL);
insert into Article
	Values('menu toqué', 18, NULL);
insert into Article
	Values('menu goulu', 10, NULL);
INSERT INTO Article
		VALUES ('menu gourmet', 50,NULL);
	
-- SERVICE
insert into Service
	Values('SOIR', '25/04/2016', 'carte repas');
insert into Service
	Values('MIDI', '25/04/2016', 'carte bar');
insert into Service
	Values('MIDI', '21/07/2015', 'carte bar');
insert into Service
	Values('SOIR', '21/07/2015', 'carte repas');
insert into Service
	Values('SOIR', '26/07/2015', 'carte repas');
insert into Service
	Values('MIDI', '26/04/2016', 'carte bar');
insert into Service
	Values('SOIR', '26/04/2016', 'carte bar');
insert into Service
	Values('SOIR', '27/07/2015', 'carte repas');
insert into Service
	Values('MIDI', '27/04/2016', 'carte bar');
insert into Service
	Values('SOIR', '28/07/2015', 'carte repas');
insert into Service
	Values('MIDI', '28/04/2016', 'carte bar');

-- RESERVATION

-- CHOIX
insert into choix
	Values('couscous');
insert into choix
	Values('gateaux maroc');
insert into choix
	Values('salade tunisienne');
insert into choix
	Values('the');
insert into choix
	Values('perrier');
insert into choix
	Values('pates bolo');
insert into choix
	Values('tiramisu');
insert into choix
	Values('salade de tomates');
insert into choix
	Values('jus orange');

insert into choix 
	Values('salade cesar');
insert into choix
	Values('steak frites');
insert into choix
	Values('coca');
insert into choix
	Values('sunday');
insert into choix
	Values('glace');
	
insert into choix
	Values('carpaccio');
insert into choix
	Values('champagne');
insert into choix
	Values('poulet');
insert into choix
	Values('fraise');
INSERT INTO  choix
	VALUES ('canard');
INSERT INTO choix
	VALUES ('pâté');
Insert INTO choix
		VALUES ('saumon');
Insert INTO choix
		VALUES ('foie gras');
INSERT INTO choix
		VALUES ('saint jacques');
INSERT INTO choix
		VALUES ('burger');
INSERT INTO choix
		VALUES ('pizza');
INSERT INTO choix
VALUES ('fondant chocolat');
INSERT INTO choix
Values ('cognac');
-- PLAT
insert into Plat
	Values('steak frites');
insert into Plat
	Values('couscous');
insert into Plat
	Values('pates bolo');
insert into Plat
	Values('poulet');
INSERT INTO  Plat
VALUES ('canard');
INSERT INTO Plat
VALUES ('pâté');
Insert INTO Plat
VALUES ('saumon');
INSERT INTO Plat
		VALUES ('burger');
INSERT INTO Plat
VALUES ('pizza');

-- ENTREE	
insert into Entree
	Values('carpaccio');
insert into Entree
	Values('salade de tomates');
insert into Entree
	Values('salade tunisienne');
insert into Entree
	Values('salade cesar');
Insert INTO Entree
VALUES ('foie gras');
INSERT INTO Entree
VALUES ('saint jacques');
-- DESSERT 
insert into Dessert
	Values('glace');
insert into Dessert
	Values('tiramisu');
insert into Dessert
	Values('fraise');
insert into Dessert
	Values('gateaux maroc');
INSERT INTO Dessert
		VALUES ('fondant chocolat');

-- BOISSON
insert into Boisson
    Values('coca');
insert into Boisson
    Values('champagne');
insert into Boisson
    Values('perrier');
insert into Boisson
    Values('the');
INSERT INTO Boisson
		Values ('cognac');

-- MENU
insert into Menu
	Values('menu enfant', 'steak frites');
insert into Menu
	Values('menu toqué', 'couscous');
insert into Menu
	Values('menu goulu', 'pates bolo');
INSERT INTO Menu
    Values ('menu gourmet', 'canard');

-- ESTRESERVEE (NumeroTable, NumeroReservation)

-- SONTCOMMANDES

-- DISPONIBLE
insert into Disponibles
	Values('coca', 'carte bar');

insert into Disponibles
	Values('perrier', 'carte bar');
	
insert into Disponibles
	Values('the', 'carte bar');
	
insert into Disponibles
	Values('coca', 'carte repas');

insert into Disponibles
	Values('perrier', 'carte repas');

insert into Disponibles
	Values('champagne', 'carte repas');
	
insert into Disponibles
	Values('couscous', 'carte repas');

insert into Disponibles
	Values('steak frites', 'carte repas');
	
insert into Disponibles
	Values('salade de tomates', 'carte repas');
	
insert into Disponibles
	Values('salade tunisienne', 'carte repas');
	
insert into Disponibles
	Values('glace', 'carte repas');
	
insert into Disponibles
	Values('tiramisu', 'carte repas');
	
insert into Disponibles
	Values('carpaccio', 'carte repas');
	
-- ESTCOMPOSE
insert into EstCompose
	Values('menu enfant', 'perrier');
insert into EstCompose
	Values('menu enfant', 'coca');
insert into EstCompose
	Values('menu enfant', 'glace');
INSERT INTO EstCompose
	Values ('menu enfant', 'burger');
INSERT INTO EstCompose
    Values ('menu enfant', 'pizza');

insert into EstCompose
	Values('menu toqué', 'salade tunisienne');
insert into EstCompose
	Values('menu toqué', 'the');
insert into EstCompose
	Values('menu toqué', 'glace');
INSERT INTO EstCompose
	Values ('menu goulu', 'burger');

INSERT INTO EstCompose
Values ('menu gourmet', 'saumon');
INSERT INTO EstCompose
Values ('menu gourmet', 'saint jacques');
INSERT INTO EstCompose
Values ('menu gourmet', 'cognac');
INSERT INTO EstCompose
Values ('menu gourmet', 'foie gras');
INSERT INTO EstCompose
Values ('menu gourmet', 'pâté');
INSERT INTO EstCompose
Values ('menu gourmet', 'fondant chocolat');
-- SONTVOISINES

insert into SontVoisines
	Values(1,3);

insert into SontVoisines
	Values(3,1);

insert into SontVoisines
	Values(3,4);

insert into SontVoisines
	Values(4,3);

insert into SontVoisines
	Values(2,5);

insert into SontVoisines
	Values(5,2);
