--ATTENTION AUX DISTINCTS, IL FAUDRA EN RAJOUTER/ENLEVER


--Consultaion

--Clients
--choix par nom
SELECT numeroclient, nomclient, telephoneclient
FROM client
WHERE nomclient='nom';


--choix par telephone
SELECT numeroclient, nomclient, telephoneclient
FROM client
WHERE telephonetelephone='telephone';


--reservation
--choix par numero de reservation
SELECT numeroreservation, nbpersonnes, prixtotal
FROM reservation
WHERE telephoneclient='telephone';


--tables
--choix par localisation
SELECT numerotable, nombreplaceisolee, nbplaceAccolee1, nbplaceAccolee2, localisation, numerogroupe	
FROM tables
WHERE localisation='localisation';

--consultation des articles?



--reserve (reservation - client)
--consulter tous les clients associés à une résevation (par nom)
SELECT distinct reserve.numeroreservation, reserve.numeroclient, reservation.nbPersonnes, client.nomClient, client.telephoneclient
FROM reserve, reservation, client
where reservation.numeroreservation = reserve.numeroreservation
and reserve.numeroclient = client.numeroclient
having numeroreservation = (SELECT numeroreservation
FROM reserve
where numeroreservation = (SELECT numeroclient
from client
where nomclient = 'le nom'));


--consulter tous les clients associés à une résevation (par telephone)
SELECT distinct reserve.numeroreservation, reserve.numeroclient, reservation.nbPersonnes, client.nomClient, client.telephoneclient
FROM reserve, reservation, client
where reservation.numeroreservation = reserve.numeroreservation
and reserve.numeroclient = client.numeroclient
having numeroreservation = (SELECT numeroreservation
FROM reserve
where numeroreservation = (SELECT numeroclient
from client
where numerotelephone = 'le num'));


--commande
--consulter les articles d'une réservation (par numero de table)
SELECT article.numeroreservation, article.nomarticle, article.quantitearticle
FROM commande, article
where commande.numeroresevation = (SELECT numeroreservation
from comprend(tables)
where 'numerotable' = comprend(tables).numerotable);



--reservation
--calcul de la facture par numero de table
SELECT article.numeroreservation, SUM((article.quantitearticle * article.prixArticle) as Facture)
FROM commande, article
where commande.numeroresevation = (SELECT numeroreservation
from comprend(tables)
where 'numerotable' = comprend(tables).numerotable);


--pas fait, le probleme est : que faut il afficher si l'on réserve plusieurs tables?
--consulter les tables disponibles pour un nombre de personnes donné
SELECT tables.numerotable, tables.nombreplacesisolee, 
FROM tables
where  
AND tables.numerotable not in (SELECT comprend.numeroreservation
FROM comprend)


--a faire :
--consulter toutes les réservations pour une date et un service
--consulter tous les articles un par un
--consulter les classe individuellement en général avec différents critères 
