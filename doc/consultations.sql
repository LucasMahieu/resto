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



--a finir
--reservation
--calcul de la facture par numero de table
SELECT article.numeroreservation, (article.quantitearticle * article.prixArticle) as Total
FROM commande, article
where commande.numeroresevation = (SELECT numeroreservation
from comprend(tables)
where 'numerotable' = comprend(tables).numerotable);
