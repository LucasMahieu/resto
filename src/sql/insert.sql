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
	Values('tiramissu', 7, 'italien');
insert into Article
	Values('salade de tomates', 8, 'italien');
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
	Values('menu enfant', 12, NULL);
insert into Article
	Values('menu toqué', 18, NULL);
insert into Article
	Values('menu goulu', 100, NULL);

	
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

insert into choix Article
	Values('pates bolo');
insert into choix
	Values('tiramissu');
insert into choix
	Values('salade de tomates');
insert into choix
	Values('jus orange');

insert into choix Article
	Values('salade cesar');
insert into choix
	Values('steak frites');
insert into choix
	Values('coca');
insert into choix
	Values('sunday');
	
insert into choix Article
	Values('carpaccio');
insert into choix
	Values('champagne');
insert into choix
	Values('poulet');
insert into choix
	Values('fraise');

-- PLAT
insert into Plat
	Values('steak frites');
insert into Plat
	Values('couscous');
insert into Plat
	Values('pates bolo');
insert into Plat
	Values('poulet');

-- ENTREE	
insert into Entree
	Values('carpaccio');
insert into Entree
	Values('salade de tomates');
insert into Entree
	Values('salade tunisienne');
insert into Entree
	Values('salade cesar');

-- DESSERT 
insert into Dessert
	Values('glace');
insert into Dessert
	Values('tiramisu');
insert into Dessert
	Values('fraise');
insert into Dessert
	Values('gateaux maroc');

-- BOISSON
insert into Boisson
    Values('coca');
insert into Boisson
    Values('champagne');
insert into Boisson
    Values('perrier');
insert into Boisson
    Values('the');
    

-- MENU
insert into Menu
	Values('menu enfant', 'steak frites');
insert into Menu
	Values('menu toqué', 'couscous');
insert into Menu
	Values('menu goulu', 'pate bolo');

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
	
insert into EstCompose
	Values('menu toqué', 'salade tunisienne');
insert into EstCompose
	Values('menu toqué', 'the');
insert into EstCompose
	Values('menu toqué', 'glace');
	
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
