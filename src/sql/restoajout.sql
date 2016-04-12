
--Ajout d'un plat 
Insert into articles Values ( 'nomArticles', 'prix', 'spécialité', 0);
--modif plat
Update articles
SET prix= ' ',
	spécialité = '',
	quantitéArticle= '',
where nomArticles= '';

--Ajout client
Insert into client Values ('numeroclient',' nomclient', 'telephoneclient'); 

--Ajout Table
Insert into Tables Values('numeroTable','nombrePlaceisolée','0','0','localisation','numeroGroupe');


--modifier table
UPDATE Tables 
SET nombrePlaceisolée= '',
	nombrePlaceAccolée1 = ' ',
	nombrePlaceAccolée2 = ' ',
	nombreGroupe = ' ' 
where numeroTable = ' ';


--Ajout réservations
Insert into Reservations Values ('numeroReservation', 'nbPersonne', 0);

--modif réservation
Update Reservations
SET nbPersonne= '',
	prixTotal = ''
where numeroReservation= ' ' ;

--
