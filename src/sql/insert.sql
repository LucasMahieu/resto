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
	
-- ARTICLES
insert into Article
	Values('couscous', 15, 'arabe');
insert into Article
	Values('salade tunisienne', 8, 'arabe');
insert into Article
	Values('glace', 5, 'italien');
insert into Article
	Values('steak frites', 10, 'americain');
insert into Article
	Values('salade de tomates', 8, 'italien');
insert into Article
	Values('carpaccio', 12, 'guineen');	
insert into Article
	Values('tiramisu', 12, 'italien');
insert into Article
	Values('coca', 3, 'americain');
insert into Article
	Values('champagne', 25, 'français');
insert into Article
	Values('perrier', 2, 'français');				
insert into Article
	Values('menu enfant', 13, NULL);
insert into Article
	Values('menu arabe', 18, 'arabe');
insert into Article
	Values('the a la menthe', 4, 'arabe');
	
-- SERVICE
insert into Service
	Values('MIDI', '25/04/2016', 'carte bar');
insert into Service
	Values('MIDI', '21/07/2015', 'carte bar');
insert into Service
	Values('SOIR', '21/07/2015', 'carte repas');

-- RESERVATION
insert into Reservation
	Values(1, 5, 120, 1, 'SOIR', '21/07/2015');
insert into Reservation
	Values(3, 5, 190, 1, 'MIDI', '21/07/2015');
insert into Reservation
	Values(2, 3, 110, 2, 'SOIR', '21/07/2015');
insert into Reservation
	Values(4, 6, 180, 3, 'SOIR', '21/07/2015');
	
-- CHOIX
insert into Choix
	Values('steak frites');
insert into Choix
	Values('couscous');
insert into Choix
	Values('carpaccio');
insert into Choix
	Values('salade de tomates');
insert into Choix
	Values('salade tunisienne');
insert into Choix
	Values('glace');
insert into Choix
	Values('tiramisu');
insert into Choix
	Values('coca');
insert into Choix
	Values('champagne');
insert into Choix
	Values('perrier');
insert into Choix
	Values('the a la menthe');
	

-- PLAT
insert into Plat
	Values('steak frites');
insert into Plat
	Values('couscous');

-- ENTREE	
insert into Entree
	Values('carpaccio');
insert into Entree
	Values('salade de tomates');
insert into Entree
	Values('salade tunisienne');

-- DESSERT 
insert into Dessert
	Values('glace');
insert into Dessert
	Values('tiramisu');

-- BOISSON
insert into Boisson
    Values('coca');
insert into Boisson
    Values('champagne');
insert into Boisson
    Values('perrier');
    
insert into Boisson
    Values('the a la menthe');

-- MENU
insert into Menu
	Values('menu enfant', 'steak frites');
insert into Menu
	Values('menu arabe', 'couscous');


-- ESTRESERVEE (NumeroTable, NumeroReservation)

insert into EstReservee
	Values(1,1);
	
insert into EstReservee
	Values(2,1);
	
	
-- SONTCOMMANDES
insert into SontCommandes
	Values('menu enfant',1,2);
	
insert into SontCommandes
	Values('couscous',1,2);
	
insert into SontCommandes
	Values('coca',1,2);

insert into SontCommandes
	Values('perrier',1,2);
	
-- DISPONIBLE

insert into Disponibles
	Values('coca', 'carte bar');

insert into Disponibles
	Values('perrier', 'carte bar');
	
insert into Disponibles
	Values('the a la menthe', 'carte bar');
	
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
	
insert into EstCompose
	Values('menu arabe', 'salade tunisienne');
insert into EstCompose
	Values('menu arabe', 'the a la menthe');
insert into EstCompose
	Values('menu arabe', 'glace');
	
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
