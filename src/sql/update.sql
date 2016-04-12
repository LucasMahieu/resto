

--modifier table
UPDATE Tables
SET nombrePlaceIsolee= '',
       	nombrePlaceAccolee1 = ' ',
        nombrePlaceAccolee2 = ' ',
        localisation = ' '
where numeroTable = ' ';

--modif réservation
Update Reservations
SET nbPersonne= '',
        prixTotal = ''
where numeroReservation= ' ' ;


--modif articles
update Article 
SET prixArticle ='',
        specialite =''
where nomArticle = '' ;



--modif service
update Service 
SET nomCarte =''
where typeService= '' and  dateService= '';

--modif reservation
update  Reservation 
        nbPersonnes ='',
        prixTotal ='',
        numeroClient = ''
where typeService= '' and dateService='' and numeroReservation='';

--modif menu

update Menu 
set nomPlatBase = ''
where nomMenu = '' ;

--modif reservee
update estReservee 
set numeroReservation =''
where numeroTable= '';


-- update sontCommandes
update sontCommandes 
set  quantiteArticle = ''
where nomArticle= '' and numeroReservation='';

