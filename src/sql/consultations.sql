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
SELECT reservation.numeroreservation, reservation.numeroclient, reservation.nbPersonnes, client.nomClient, client.telephoneclient
FROM reservation, client
where reservation.numeroclient = client.numeroclient
having reservation.numeroreservation = (SELECT numeroreservation
FROM client
where client.numeroreservation = (SELECT numeroclient
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
	SELECT sontcommandes.numeroreservation, article.nomarticle, article.quantitearticle
FROM sontcommandes, article
where sontcommandes.numeroresevation = (SELECT numeroreservation
from estreserve
where estreserve.numerotable = 'numerotable');



--reservation
--calcul de la facture par numero de table
SELECT sontcommandes.numeroreservation, SUM((article.quantitearticle * article.prixArticle) as Facture)
FROM commande, article
where commande.numeroresevation = (SELECT numeroreservation
from estreserve
where estreserve.numerotable = 'numerotable');


--pas fait, le probleme est : que faut il afficher si l'on réserve plusieurs tables?
--consulter les tables disponibles pour un nombre de personnes donné
--SELECT T.numerotable, T.nombreplacesisolee, 
--FROM tables T, estreserve E
--where T.numerotable not in (SELECT numeroreservation From estreserve)

--having (T.nombreplacesisolee >= 'nombre' 

--or T.nombrePlaceAccolee1 >= 'nombre'

--or (T.nombreplacesisolee + (SELECT TT.nombreplacesisolee, SS.numerotable1, SS.numerotable2
--FROM tables TT, sontvoisines SS
--where SS.numerotable1 = T.numerotbale
--having TT.numerotable not in (SELECT numeroreservation From estreserve)
--and sontvoisines.numerotable1 = 
-- >= 'nombre'

--or tables.nombrePlaceAccolee1 + >= 'nombre'
--and min(tables.nombreplacesisolee
--AND tables.numerotable not in (SELECT comprend.numeroreservation
--FROM comprend)



--consulter toutes les réservations pour une date et un service
SELECT reservation.dateservice, reservation.typeservice, reservation.numeroreservation, reservation.nbPersonnes,reservation.prixtotal, client.nomclient
FROM reservation, client
where reservation.numeroreservation = client.numeroreservation
and reservation.dateservice = "dates"
and reservation.typeservice = "type";


--consulter tous les articles par nom
SELECT * 
from article
where article.nom = 'nom';

--consulter tous les menus/boissons/plats///
SELECT * 
from article
having article.nomarticle in (SELECT * 
from 'a coisir');


--a faire :
