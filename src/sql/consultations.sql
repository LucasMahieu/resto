--ATTENTION AUX DISTINCTS, IL FAUDRA EN RAJOUTER/ENLEVER
--Fait : tables, client 

--Consultaion
-----------------------------------------------------------------
--Clients
--choix par nom/telephone
SELECT numeroclient, nomclient, telephoneclient
FROM client
WHERE nomclient = 'Francine'
and telephoneclient = '0601022222';


--tables
--choix par localisation/numerotable	
SELECT distinct nombreplaceisolee, localisation
FROM tables
WHERE localisation='cheminee'
and nombreplaceisolee > 1;


--reservation
--consulter toutes les réservations pour une date/service/nom/tel
SELECT distinct reservation.dateservice, reservation.typeservice, reservation.numeroreservation, reservation.nbPersonnes, reservation.prixtotal, client.nomclient, client.telephoneclient
FROM reservation, client
where reservation.dateservice = '21/07/2015'
and reservation.typeservice = 'SOIR'
and client.nomclient = 'Johnny'
and client.telephoneclient = '0601010101';


--consulter les articles d'une réservation (par numero de table), il faut aussi la date te le type
SELECT sontcommandes.numeroreservation, article.nomarticle, sontcommandes.quantitearticle
FROM sontcommandes, article, reservation
where reservation.dateservice = '21/07/2015'
and reservation.typeservice = 'SOIR'
and sontcommandes.numeroreservation = (SELECT numeroreservation
from estreservee
where estreservee.numerotable = '1');

--a faire dans les updates
--calcul de la facture par numero de table
SELECT sontcommandes.numeroreservation, client.nomclient, SUM(sontcommandes.quantitearticle * article.prixArticle) as Facture
FROM sontcommandes, article, client, reservation
where reservation.numeroclient = clent.numeroclient
and estreservee.numerotable = 'numero'
and estreservee.numeroreservation = reservation.numeroreservation
and 


--facture par numerotable, il faut aussi la date te le type
SELECT reservation.numeroreservation, nbpersonnes, prixtotal
FROM reservation, estreservee
where reservation.dateservice = '21/07/2015'
and reservation.typeservice = 'SOIR'
and reservation.numeroreservation = estreservee.numeroreservation
and estreservee.numerotable = (SELECT numerotable
from estreservee
where estreservee.numerotable = 1);


--facture par nomclient, il faut aussi la date te le type
SELECT numeroreservation, nbpersonnes, prixtotal
FROM reservation
where reservation.dateservice = '21/07/2015'
and reservation.typeservice = 'SOIR'
and reservation.numeroclient = (SELECT numeroclient
from client
where nomclient = 'Johnny');


--articles
--consulter tous les articles par nom/specialite/prix
SELECT * 
from article
where article.nomarticle LIKE '%';
and article.specialite = 'la spec'
and article.prix >= 'prixmin'
and article.prix <= 'prixmax';


--consulter tous les menus/boissons/plats/// +par nom/specialite/prix
SELECT * 
from article
where article.nom = 'nom'
and article.specialite = 'la spec'
and article.prix >= 'prixmin'
and article.prix <= 'prixmax';
having article.nomarticle in (SELECT * 
from 'a choisir');



--consulter les tables disponibles pour un nombre de personnes donné, marche vraissembleblement pas
--renvoie le nombre max de tables par localisation
SELECT count(*)
FROM tables
group by tables.localisation



--le n dépendra du nombre max de tables renvoyé par la requête d'avant
SELECT T1.numerotable, T2.numerotable, Tn.numerotable, SUM(T1.nombreplaceisolee + T2.nombreplaceisolee + Tn.nombreplaceisolee) as somme

CASE
		when somme >= 'nombre' THEN 'OK' ELSE '0'
END

FROM estreservee E, tables T1, tables T2, tables Tn

WHERE T1.numerotable not in (SELECT numeroreservation From estreservee)
and T2.numerotable not in (SELECT numeroreservation From estreservee)
and Tn.numerotable not in (SELECT numeroreservation From estreservee)
and exists (SELECT SS.numerotable1, SS.numerotable2 From sontvoisine SS where (SS.numerotable1 = T1.numerotable and SS.numerotable2 = T2.numerotable) or (SS.numerotable1 = T2.numerotable and SS.numerotable2 = T2.numerotable)
and exists (SELECT SS.numerotable1, SS.numerotable2 From sontvoisine SS where (SS.numerotable1 = T2.numerotable and SS.numerotable2 = Tn.numerotable) or (SS.numerotable1 = Tn.numerotable and SS.numerotable2 = T2.numerotable);







