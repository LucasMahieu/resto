--supprimer une table
delete from tables where numeroTable='';

--supprimer un plat
delete from Article where nomArticle = '';

--supprimer client
delete from Client where numeroClient= '';

--supprimer service
delete from Service where typeService= '' and dateService='';


--supprimer reservations
delete from Reservation where numeroReservation='' ;

