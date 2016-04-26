package ControleurResto;

import VueResto.*;
import ModeleResto.*;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.*;

/**
 * Tous les attribue de cette classe doivent etre static !!!!!!!!
 * De la sorte, chaque inteface pourra avoir un attribut controleur 
 * et les informations, états, variables du controleur sera cohérent 
 * entre toutes les autres classes
 *
 *  Ce n'est plus utile avec le patron Singleton car instance unique de cette classe
 *
 */
public class Controleur{
    private int numResaCmdSelectionee;
    private int numResaSuiviSelectionee;
    private Date date;
    private final SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat sdfHeure = new SimpleDateFormat("HH");
    private String dateNow;
    private String heureNow;
    private String serviceNow;
    private final int DEBUT_SERVICE_SOIR = 17;

    final private static Controleur instanceUnique = new Controleur();

    /** 
     * Constructeur du controleur 
     */
    private Controleur() {
        numResaCmdSelectionee = 0;
        numResaSuiviSelectionee = 0;
        date = new Date();
        dateNow = sdfDate.format(date);
        heureNow = sdfHeure.format(date);
        serviceNow = "MIDI";
        if(Integer.parseInt(heureNow) >= DEBUT_SERVICE_SOIR){
            serviceNow = "SOIR";
        }

        initRes(ReservationFactoryConcrete.get().getReservations());
    }

    /*
     * Ajoute dans l'état initial, au lancement de l'application
     * les réservations d'aujourd'hui et futures.
     */
    public int initRes(HashMap<Integer, ReservationConcrete> reservations) {
		try {
			ResultSet rset = ReservationFactoryConcrete.get().initRes(reservations);
            if (rset == null) {
                return -1;
            }
			while (rset.next()) {
				int numRes = rset.getInt(1);
                String date = rset.getString(2);
                if (turfu(date)) {
                    ReservationConcrete newRes = new ReservationConcrete(numRes);
                    HashMap<String, Integer> choix = getChoixCommandes(numRes);
                    // On affiche les articles de la réservation trouvée
                    for (Map.Entry<String,Integer> articleSuivi : choix.entrySet()) {

                        Object[] o = {articleSuivi.getKey(),articleSuivi.getValue()};
                        newRes.getSuivi().ajouterArticle(ReservationFactoryConcrete.get().getArticleBD().typeArticle(articleSuivi.getKey()), articleSuivi.getKey(), articleSuivi.getValue());
                    }
                    reservations.put(numRes, newRes);
                }
			}
            rset.close();
            ReservationFactoryConcrete.get().getStmt().close();
            return 0;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête initRes.");
			e.printStackTrace(System.err);
			return -1;
		}
    }

    public boolean turfu(String date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
        Date nowDate;
        Date thisDate;
        try {
            nowDate = df.parse(dateNow);
            thisDate = df.parse(date);
            if (nowDate.compareTo(thisDate) <= 0) {
                return true;
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    /** 
     * Accesseur du controleur 
     *	@return controleur
     */
    public static Controleur get()
    {
        return instanceUnique;
    }

    /** 
     * Ajoute à la BD la quantité d'article 'nom' à la reservation numResa
     *	@param nom nom de l'article
     *	@param quantite quantite
     *	@param numResa numero de reservation
     *	@return 0 si réussi, -1 sinon
     */
    public int ajouterArticle(String nom, int quantite, int numResa){
        // ajouter à la resa l'article donner avec les bonnes quantités dans la BD
        int ret = ReservationFactoryConcrete.get().getArticleBD().ajoutArticle(nom, quantite, numResa);
        ReservationConcrete thisRes = ReservationFactoryConcrete.get().getReservations().get(numResa);
        String typeArticle = ReservationFactoryConcrete.get().getArticleBD().typeArticle(nom);
        if (thisRes == null) {
            return -1;
        }
        if (thisRes.getSuivi().ajouterArticle(typeArticle, nom, quantite) == -1) {
            return -1;
        }
        return ret;
    }

    /** 
     * Ajoute à la BD la quantité de menus 'nom' à la reservation numResa + VERIFIE qu'il existe pas déja
     *	@param nomMenu nom du menu
     *	@param quantite quantite
     *	@param numResa numero de reservation
     *	@param boisson nom de la boisson
     *	@param entree nom de l entree
     *	@param plat nom du plat
     *	@param dessert nom du dessert
     *	@return 0 si réussi, -1 sinon
     */
    public int ajouterMenu(String nomMenu, int quantite, int numResa, String  boisson, String entree, String plat, String dessert){
        // ajouter à la resa l'article donner avec les bonnes quantités dans la BD
	int ret = ReservationFactoryConcrete.get().getArticleBD().ajoutMenu(nomMenu, quantite, numResa, boisson, entree, plat, dessert);
	ReservationConcrete thisRes = ReservationFactoryConcrete.get().getReservations().get(numResa);
	if (thisRes == null) {
            return -1;
        }
	if (thisRes.getSuivi().ajouterMenu(boisson, entree, plat, dessert, quantite) == -1) {
            return -1;
        }
	return ret;
    }

    /** 
     * Supprime à la BD le menu 'nomMenu' à la reservation numResa, DANS MENU ET DANS MENUCOMMANDES
     *	@param nomMenu nom du menu
     *	@param quantite quantite
     *	@param numResa numero de reservation
     *	@param boisson nom de la boisson
     *	@param entree nom de l entree
     *	@param plat nom du plat
     *	@param dessert nom du dessert
     *	@return 0 si réussi, -1 sinon
     */
    public int supprimerMenu(String nomMenu, int quantite, int numResa,String  boisson, String entree, String plat, String dessert){
        // supprimer à la resa l'article donné avec les bonnes quantités dans la BD
        if (ReservationFactoryConcrete.get().getArticleBD().dejaCommandeMenuCommandes(nomMenu, numResa, boisson, entree, plat, dessert) > 0) {
            return ReservationFactoryConcrete.get().getArticleBD().supprimerMenu(nomMenu, numResa, quantite, boisson, entree, plat, dessert);	
        }
        return -1;
    }


    /** 
     *  Supprime à la BD la quantité d'article 'nom' à la reservation numResa
     *	@param nom nom de l'article
     *	@param quantite quantite 
     *	@param numResa numero de reservation
     *	@return 0 si réussi, -1 sinon
     */
    public int supprimerArticle(String nom, int quantite, int numResa){
        // supprimer à la resa l'article donner avec les bonnes quantités dans la BD
        int ret = ReservationFactoryConcrete.get().getArticleBD().supprimerArticle(nom, quantite, numResa);
        ReservationConcrete thisRes = ReservationFactoryConcrete.get().getReservations().get(numResa);
        String typeArticle = ReservationFactoryConcrete.get().getArticleBD().typeArticle(nom);
        if (thisRes == null) {
            return -1;
        }
        if (thisRes.getSuivi().supprimer(typeArticle, nom, quantite) == -1) {
            return -1;
        }
        return ret;
    }

    /** Cette fonction permet de trouver les articles correspondant a la reservation
     *
     *	@param numResa numero de la reservation
     *	@return HashMap des articles commandes
     */
    public HashMap<String, Integer> getArticlesCommandes(int numResa) {
        HashMap<String, Integer> h = new HashMap<String, Integer>();
        h.putAll(ReservationFactoryConcrete.get().getArticleBD().getArticlesCommandes(numResa, "Entree"));
        h.putAll(ReservationFactoryConcrete.get().getArticleBD().getArticlesCommandes(numResa, "Plat"));
        h.putAll(ReservationFactoryConcrete.get().getArticleBD().getArticlesCommandes(numResa, "Dessert"));
        h.putAll(ReservationFactoryConcrete.get().getArticleBD().getArticlesCommandes(numResa, "Boisson"));
        h.putAll(ReservationFactoryConcrete.get().getArticleBD().getArticlesCommandes(numResa, "Menu"));
        return h;
    }

    /** Cette fonction permet de creer une reservation
     * @param client nom du client
     *
     *	@return ?
     */
    public int creerFacture(String client){
        // Facture factureFinale = new Facture();
        return 999;
    }

    /** Cette fonction permet de trouver la date actuelle
     *
     *	@return date actuelle
     */
    public String getDateNow(){
        date = new Date();
        dateNow = sdfDate.format(date);
        return this.dateNow;
    }

    /** Cette fonction permet de trouver l'heure actuelle
     *
     *	@return heure actuelle
     */
    public String getHeureNow(){
        date = new Date();
        heureNow = sdfHeure.format(date);
        return this.heureNow;
    }

    /** Cette fonction permet de trouver l'etat de la commande de la reservation selectionnee
     *
     *	@param numReservation numero de reservation
     *	@return numero du client 
     */
    public String getEtatCommande(int numReservation){
        ReservationConcrete thisRes = ReservationFactoryConcrete.get().getReservations().get(numReservation);
        if (thisRes == null) {
            return null;
        }
        return thisRes.getSuivi().getEtatCommande();
    }

    /** Cette fonction permet de trouver le service actuel
     *
     *	@return numero du client 
     */
    public String getServiceNow() {
        serviceNow = "MIDI";
        if(Integer.parseInt(getHeureNow()) >= DEBUT_SERVICE_SOIR){
            serviceNow = "SOIR";
        }
        return this.serviceNow;
    }

    /**
     * Cette fonction doit :
     *		- Vérifier si il y a un service ce jour là
     *		- Trouver un groupe de table optimal
     *		- Trouver le client (si il existe, sinon le créer)
     *		- Créer la réservation
     *		- Associer la table à la résa
     *		- Retourner le numero de résa
     *	@param nom nom du client qui réserve 
     *	@param date date de la reservation
     *	@param service pour lequel le client réserve
     *	@param nbPersonnes nombre de personnes qui souhaitent réserver
     *	@param localisation de la table souhaitée
     *	@param tel téléphone du client
     *	@return numéro de la résa créé : -1 en cas d'erreur, 0 en cas d'indisponibilité
     */
    public int creerReservation(String nom, String date, String service, int nbPersonnes, String localisation, String tel) {
        // Vérification du service
        if (!ReservationFactoryConcrete.get().getServiceBD().presenceService(date, service)) {
            return 0;
        }

        // Vérification des tables
        ArrayList<Integer> tablesArray = trouverTable(localisation, date, service, nbPersonnes);
        System.out.println("tableau=" + tablesArray);
        if(tablesArray == null){
            //erreur
            System.out.println("tablesArray=null");
            return -1;
        }else if(tablesArray.size()==0){
            //Pas de table dispo
            System.out.println("tablesArray.size()=0");
            return 0;
        }
        System.out.println("tables sont dispo");

        // Vérification si le client existe / création nouveau client
        int numClient = trouverClient(nom,tel);
        if(numClient == -1){
            //erreur
            System.out.println("erreur lors de recherche client");
        }

        // Création de la réservation
        int numResa = ReservationFactoryConcrete.get().creerReservation(numClient, date, service, nbPersonnes);
        if(numResa<0){
            //erreur
            System.out.println("erreur creation resa");
            return -1;
        }
        if(numResa==0){
            System.out.println("problème creation resa");
            return numResa;
        }
        numResaSuiviSelectionee = numResa;
        numResaCmdSelectionee = numResa; 
        // ici la resa est crée et son num se trouve dans tmp
        // On associe donc la(les) table(s) trouvée(s) à ce num de resa
        for (int i = 0; i < tablesArray.size(); i++) {
            if (ReservationFactoryConcrete.get().getTableBD().ajouterTable(tablesArray.get(i),numResa) != 0) {
                System.out.println("problème lors des ajouts de table");
                return -1;
            }
        }
        ReservationFactoryConcrete.get().validate();
        return numResa;
    }

    public void validate() {
        ReservationFactoryConcrete.get().validate();
    }

    /** Cette fonction permet de trouver le numero du client 
     *
     *	@param nomC nom du client
     *	@param telC numero de telephone du client
     *	@return numero du client 
     */
    public int trouverClient(String nomC, String telC){
        int numClient = ReservationFactoryConcrete.get().getClientBD().existsClient(nomC, telC);
        if(numClient == -1){
            return -1;
        }else if (numClient == 0){
            numClient = ReservationFactoryConcrete.get().getClientBD().create(nomC, telC);
            if(numClient == -1){
                return -1;
                // si ca n'a pas marcher, peu etre re essayer ?
            }
        }
        return numClient;
    }

    /** Cette fonction permet de trouver une table pour la reservation demandée
     *
     *	@param localisation localisation demandée par le client
     *	@param date date demandée par le client
     *	@param service service demandé par le client
     *	@param nbPersonnes nombre de personnes
     *	@return liste de tables correspondantes
     */
    public ArrayList<Integer> trouverTable(String localisation, String date, String service, int nbPersonnes){
        //ArrayList<Integer> res = new ArrayList<Integer>();
        LinkedList<Integer> res;
        ArrayList<Integer> table = new ArrayList<Integer>();
        try {
            res = ReservationFactoryConcrete.get().getTableBD().getTableLibre(localisation, date, service);
            if (res == null) {
                throw (new SQLException());
            }
            System.out.println("table libre = " + res);
            // Res contient toutes les tables libres pour ce jour, service , localisation
            int nbPlace = 0;
            int resteMin = 1000;
            int config = 0;
            int tableIdeal = 0;

            /*
             * CAS OU UNE SEULE TABLE SUFFIRAIT
             */

            for (Integer i : res) {
                nbPlace = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(i,config);
                // On regardee toutes les tables pour voir si une seule pourrait suffir.
                // On regarde celle qui produit le plus petit reste.
                if(nbPlace>=nbPersonnes){
                    if(nbPlace-nbPersonnes<resteMin){
                        resteMin = nbPlace - nbPersonnes;
                        tableIdeal = i;
                    }
                }
            }
            // Si tableIdeal != 0 alors il y a une table qui convient sans accolement
            if(tableIdeal!=0){
                table.add(tableIdeal);
                return table;
            }
            /*
             * CAS OU DEUX TABLES SUFFIRAIENT
             */
            config = 1;
            // tableVoisine[] : numéros des tables voisines de la table considérée.
            int tableVoisine[] = {0,0};
            // nbPlaceVoisine[] : nombre de places des tables voisines de la table considérée,
            // indexées dans ce tableau de la même façon que dans tableVoisine[]
            int nbPlaceVoisine[] = {0,0};
            // j : nombre de tables voisines de la table considérée.
            int j=0;
            // tableIdeoAccolee[] : les deux tables qu'on accolera finalement si cela marche.
            int tableIdealAccolee[] = {0,0};
            resteMin = 1000;
            for(Integer i : res) {
                // Réinitialisation des tableaux
                tableVoisine[0] = tableVoisine[1] = 0;
                nbPlaceVoisine[0] = nbPlaceVoisine[1] = 0;

                // Pour chaque table libre, on regarde ses voisines.
                nbPlace = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(i,config);

                LinkedList<Integer> voisines = ReservationFactoryConcrete.get().getTableBD().getTableVoisine(i);
                // Si la table considérée n'a pas de voisines, on regarde la suivante.
                if (voisines.isEmpty()) {
                    continue;
                }
                else{
                    j=0;
                    for (Integer t : voisines) {
                        tableVoisine[j++] = t;
                    }
                }
                System.out.println("voisines: " + voisines);
                System.out.println("tableVoisine[0] = " + tableVoisine[0]);
                System.out.println("tableVoisine[1] = " + tableVoisine[1]);

                // On en déduit le nombre de places des tables voisines si on les accole uniquement avec la table considérée.
                // Ce nombre peut être 0 ou -1 si la table voisine n'a pas de place ou s'il n'y en a qu'une seule.
                nbPlaceVoisine[0] = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(tableVoisine[0], 1);
                nbPlaceVoisine[1] = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(tableVoisine[1], 1);
                // on a donc le nombre de place accolée1 des deux voisines 
                // On regarde si un tel nombre de place est suffisant
                // et on garde les tables qui permettent d'obtenir un reste minimum.
                if(nbPlace+nbPlaceVoisine[0]>=nbPersonnes){
                    if(nbPlace+nbPlaceVoisine[0]-nbPersonnes<resteMin){
                        resteMin = nbPlace+nbPlaceVoisine[0] - nbPersonnes;
                        tableIdealAccolee[0] = i;
                        tableIdealAccolee[1] = tableVoisine[0];
                    }
                }
                if(nbPlace+nbPlaceVoisine[1]>=nbPersonnes){
                    if(nbPlace+nbPlaceVoisine[1]-nbPersonnes<resteMin){
                        resteMin = nbPlace+nbPlaceVoisine[1] - nbPersonnes;
                        tableIdealAccolee[0] = i;
                        tableIdealAccolee[1] = tableVoisine[1];
                    }
                }
            }

            // Si on a trouvé deux tables qui conviennent, on les ajoutent à l'ensemble
            // de résultat et on le renvoit.
            if(tableIdealAccolee[0]!=0 && tableIdealAccolee[1]!=0){
                table.add(tableIdealAccolee[0]);
                table.add(tableIdealAccolee[1]);
                return table;
            }

            /*
             * CAS OU TROIS TABLES SUFFIRAIENT
             */

            config = 2;
            j=0;
            int tableIdealAccolee2[] = {0,0,0};
            resteMin = 1000;
            for(Integer i : res){
                // Réinitialisation des tableaux
                tableVoisine[0] = tableVoisine[1] = 0;
                nbPlaceVoisine[0] = nbPlaceVoisine[1] = 0;

                // Table i
                nbPlace = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(i, config);
                // les tables voisines de i
                LinkedList<Integer> voisines2 = ReservationFactoryConcrete.get().getTableBD().getTableVoisine(i);
                if (voisines2.isEmpty()) {
                    // Pas de voisin, on passe à i++
                    continue;
                }
                else{
                    j=0;
                    for (Integer t : voisines2) {
                        tableVoisine[j++] = t;
                    }
                }
                //On recupere le nombre de places des 2 voisins pour un accolement à i
                //ATENTION les tables voisines vont etre en accolement simple
                //seul i est en accolement double -> on utilise config-1 pour les voisines
                nbPlaceVoisine[0] = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(tableVoisine[0], 1);
                nbPlaceVoisine[1] = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(tableVoisine[1], 1);
                // on a donc le nombre de place accolée1 des deux voisines 
                if (nbPlace + nbPlaceVoisine[0] + nbPlaceVoisine[1] >= nbPersonnes) {
                    if(nbPlace+nbPlaceVoisine[0]+nbPlaceVoisine[1]-nbPersonnes<resteMin){
                        resteMin = nbPlace+nbPlaceVoisine[0]+nbPlaceVoisine[1] - nbPersonnes;
                        tableIdealAccolee2[0] = i;
                        tableIdealAccolee2[1] = tableVoisine[0];
                        tableIdealAccolee2[2] = tableVoisine[1];
                    }
                }
            }
            if(tableIdealAccolee2[0]!=0 && tableIdealAccolee2[1]!=0 && tableIdealAccolee2[2]!=0){
                table.add(tableIdealAccolee2[0]);
                table.add(tableIdealAccolee2[1]);
                table.add(tableIdealAccolee2[2]);
                return table;
            }
            // Si on arrive la, c'est qu'il y a pas de table assez grande
            // dans la localisation donnée Donc on fait la recherche dans les autres loc
            if (localisation != null){
                table = trouverTable(null,date,service,nbPersonnes);
            }
        }
        catch (SQLException e) {
            System.err.println("Erreur dans l'algorithme pour trouver les tables.");
            e.printStackTrace(System.err);
        }
        return table;
    }

    public LinkedList<Integer> getListeReservations() {
        LinkedList<Integer> ret = new LinkedList<Integer>();
        Set<Integer> setN = ReservationFactoryConcrete.get().getReservations().keySet();
        ret.addAll(setN);
        return ret;
    }

    public String getService(int numResa) {
        return ReservationFactoryConcrete.get().getServiceBD().getService(numResa);
    }

    public int getNombrePersonnes(int numResa) {
        return ReservationFactoryConcrete.get().getNombrePersonnes(numResa);
    }

    /** 
     *  Vérifie que la réservation existe, puis la supprime
     *	@param numeroTable numéro de table
     *	@param date date de la suppression 
     *	@param service service correspondant à la suppression
     *	@return 0 si réussite, -1 sinon
     */
    public int supprimerReservation(int numeroTable, String date, String service){
        if (ReservationFactoryConcrete.get().getTableBD().getNumeroReservation(numeroTable, date, service) == 0) {
            ReservationFactoryConcrete.get().getTableBD().supprimerReservation(numeroTable, date, service);
            return 0;
        } 
        return -1;
    }

    /* 
     * TODO : ne selectionner que les articles disponibles sur la carte, fonction qui ne marche pas pour le moment
     * à la date actuelle !!
     */

    /** 
     *  Renvoie la liste des articles pour un service donné
     *	@param type nom du service
     *	@return Liste d'articles correspondant au service demandé
     */
    public LinkedList<String> getListeArticles(String type)
    {
        LinkedList<String> resultat = new LinkedList<String>();
        try {
            ResultSet rset = ReservationFactoryConcrete.get().getArticleBD().getArticle(null, -1, null, type);
            if (rset == null) {
                return resultat;
            }
            while(rset.next()){
                resultat.add(rset.getString(1));
            }
            rset.close();
            ReservationFactoryConcrete.get().getArticleBD().getStmt().close();
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête.");
            e.printStackTrace(System.err);
        }
        return resultat;
    }

    /** 
     *  Retourne les articles d'un certain type qui sont disponibles pour un menu donné
     *	@param nomMenu nom du menu
     *	@param type nom du service
     *	@return Liste d'articles correspondant au menu
     */
    public LinkedList<String> getListeArticlesMenu(String nomMenu, String type)
    {
        if (type == "Menu") {
            return null;
        }
        LinkedList<String> resultat = ReservationFactoryConcrete.get().getArticleBD().getArticleMenu(nomMenu, type);
        return resultat;
    }


    /** 
     *  Retourne le prix d'un article
     *	@param nomArticle nom de l'article
     *	@return prix de l'article en euros
     */
    public float getPrixArticle(String nomArticle)
    {
        float resultat = -1;
        resultat = ReservationFactoryConcrete.get().getArticleBD().getPrix(nomArticle);
        return resultat;
    }

    /** 
     *  Retourne le numero de reservation correspondant aux paramètres
     *	@param date date de la reservation
     *	@param nTable numero de la table
     *	@param service type de service
     *	@return numéro de la reservation
     */
    public int getNumeroReservation(String date, int nTable, String service){
        return ReservationFactoryConcrete.get().getTableBD().getNumeroReservation(nTable, date, service);
    }


    /** 
     *  Retourne le numero de reservation correspondant aux paramètres
     *	@param numTable numero de la table
     *	@return numéro de la reservation du service courant
     */
    public int getNumeroReservation(int numTable){
        return ReservationFactoryConcrete.get().getTableBD().getNumeroReservation(numTable, dateNow, serviceNow);
    }

    /** 
     *  Donne une string comportant toutes les tables associée à une réservation
     *  Convention : séparer les numeros par des '-'.
     *	@param numResa numero de la reservation
     *	@return liste de tables correspondant a la reservation
     */
    public LinkedList<Integer> getNumeroTables(int numResa) {
        if (numResa <= 0) {
            return null;
        }
        LinkedList<Integer> tables = ReservationFactoryConcrete.get().getTableBD().getNumeroTable(numResa);
        return tables;
    }

    /** 
     *  Renvoie le nom du client pour la reservation demandee
     *	@param numResa numero de la reservation
     *	@return nom du client
     */
    public String getNom(int numResa) {
        String resultat = ReservationFactoryConcrete.get().getTableBD().getNomRes(numResa);
        return resultat;
    }

    /** 
     *  Renvoie le numero de la reservation selectionnee dans l'onglet commande
     *	@return numero de la reservation selectionnee dans l'onglet commande
     */
    public int getNumResaCmdSelectionne(){
        return numResaCmdSelectionee;
    }

    /** 
     *  Renvoie le numero de la reservation selectionnee dans l'onglet suivi
     *	@return numero de la reservation selectionnee dans l'onglet suivi
     */
    public int getNumResaSuiviSelectionne(){
        return numResaSuiviSelectionee;
    }

    /** 
     *  paramètre le numero de la reservation selectionnee dans l'onglet commande
     *	@param n numero de la reservation
     */
    public void setNumResaCmdSelectionne(int n){
        numResaCmdSelectionee = n;
    }

    /** 
     *  paramètre le numero de la reservation dans l'onglet suivi
     *	@param n numero de la reservation
     */
    public void setNumResaSuiviSelectionne(int n){
        numResaSuiviSelectionee = n;
    }

    /** 
     *  Renvoie les articles selectionnes correspondant a la reservation
     *	@param numResa numero de la reservation
     *	@return HashMap du choix d'articles
     */
    public HashMap<String, Integer> getChoixCommandes(int numResa) {
        HashMap<String, Integer> h = new HashMap<String, Integer>();
        if (ReservationFactoryConcrete.get() == null) {
            System.out.println("TEST");
        }
        HashMap<String, Integer> boissons = ReservationFactoryConcrete.get().getArticleBD().getArticlesCommandes(numResa, "Boisson");
        if (boissons != null) {
            h.putAll(boissons);
        }
        HashMap<String, Integer> entrees = ReservationFactoryConcrete.get().getArticleBD().getArticlesCommandes(numResa, "Entree");
        if (entrees != null) {
            h.putAll(entrees);
        }
        HashMap<String, Integer> plats = ReservationFactoryConcrete.get().getArticleBD().getArticlesCommandes(numResa, "Plat");
        if (plats != null) {
            h.putAll(plats);
        }
        HashMap<String, Integer> desserts = ReservationFactoryConcrete.get().getArticleBD().getArticlesCommandes(numResa, "Dessert");
        if (desserts != null) {
            h.putAll(desserts);
        }
        for (String choix : ReservationFactoryConcrete.get().getArticleBD().getArticlesMenuCommandes(numResa)) {
            if (h.containsKey(choix)) {
                h.put(choix, h.get(choix) + 1);
            } else {
                h.put(choix, 1);
            }
        }
        return h;
    }

    /** 
     *  Renvoie les articles a envoyer correspondant a la reservation
     *	@param numeroReservation numero de la reservation
     *	@return HashMap des articles a envoyer
     */
    public HashMap<String, Integer> aEnvoyer(int numeroReservation) {
        ReservationConcrete thisRes = ReservationFactoryConcrete.get().getReservations().get(numeroReservation);
        System.out.println(ReservationFactoryConcrete.get().getReservations());
        if (thisRes == null) {
            System.out.println("nullThisRes");
            return null;
        }
        return thisRes.getSuivi().aEnvoyer();
    }

    /** 
     *  paramètre l' article qui ont ete envoyes correspondant a la reservation
     *	@param nomArticle nom de l'article envoye
     *	@param numeroReservation numero de la reservation
     *	@param quantite quantite
     *	@return 0 si réussi, -1 sinon
     */
    public int estEnvoye(String nomArticle, int numeroReservation, int quantite) {
        ReservationConcrete thisRes = ReservationFactoryConcrete.get().getReservations().get(numeroReservation);
        if (thisRes == null) {
            return -1;
        }
        String typeArticle = ReservationFactoryConcrete.get().getArticleBD().typeArticle(nomArticle);
        if (typeArticle == null) {
            return -1;
        }
        return thisRes.getSuivi().estEnvoye(typeArticle, nomArticle, quantite);
    }
    /**
     * Retourne, pour un type, (boisson, entree ..), les articles comandés pour un numresa
     */

    /** 
     *  Renvoie les articles correspondant a la reservation demandee et a l'etat du repas
     *	@param numResa numero de la reservation
     *	@param type etat du repas
     *	@return HashMap du choix d'articles correspondant à la reservation et au etat du repas
     */
    public HashMap<String, Integer> getChoixCommandes(int numResa, String type) {
        HashMap<String, Integer> h = new HashMap<String, Integer>();
        h.putAll(ReservationFactoryConcrete.get().getArticleBD().getArticlesCommandes(numResa, type));
        for (String choix : ReservationFactoryConcrete.get().getArticleBD().getArticlesMenuCommandesType(numResa, type)) {
            if (h.containsKey(choix)) {
                h.put(choix, h.get(choix) + 1);
            } else {
                h.put(choix, 1);
            }
        }
        return h;
    }	
}
