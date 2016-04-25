package ControleurResto;

import VueResto.*;
import ModeleResto.*;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Tous les attribue de cette classe doivent etre static !!!!!!!!
 * De la sorte, chaque inteface pourra avoir un attribut controleur 
 * et les informations, états, variables du controleur sera cohérent 
 * entre toutes les autres classes
 *
 * -> Ce n'est plus utile avec le patron Singleton car instance unique de cette classe
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

    private Controleur(){
        numResaCmdSelectionee = 0;
        numResaSuiviSelectionee = 0;
        date = new Date();
        dateNow = sdfDate.format(date);
        heureNow = sdfHeure.format(date);
        serviceNow = "midi";
        if(Integer.parseInt(heureNow) >= DEBUT_SERVICE_SOIR){
            serviceNow = "soir";
        }
    }

    public static Controleur get()
    {
        return instanceUnique;
    }

	/**
	 * Ajoute à la BD la quantité d'article 'nom' à la reservation numResa
	 */
	public int ajouterArticle(String nom, int quantite, int numResa){
		// ajouter à la resa l'article donner avec les bonnes quantités dans la BD
        return 666;
	}

	public int creerFacture(String client){
       // Facture factureFinale = new Facture();
       return 999;
    }

    public String getDateNow(){
        date = new Date();
        dateNow = sdfDate.format(date);
        return this.dateNow;
    }

    public String getHeureNow(){
        date = new Date();
        heureNow = sdfHeure.format(date);
        return this.heureNow;
    }

    public String getServiceNow() {
        serviceNow = "MIDI";
        if(Integer.parseInt(getHeureNow()) >= DEBUT_SERVICE_SOIR){
            serviceNow = "MIDI";
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
				System.out.println("probleme lors des ajouts de table");
				return -1;
			}
		}
		return numResa;
    }

	public int trouverClient(String nomC, String telC){
		int numClient = ReservationFactoryConcrete.get().getClientBD().exists(nomC, telC);
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

    public void modifierReservation(String nom, String prenom, int nbPersonnes, String date, String service, String localisation){
        //Vérification de l'existence de la réservation 
        //Vérification des disponibilités des tables
        //si ok:
        //Appel à la modification de réservation dans la BD
    }

    public void supprimerReservation(String nom, String prenom, int nbPersonnes, String date, String service, String localisation){
        //Vérification de l'existence de la réservation 
        //si ok:
        //Appel à la suppression de réservation dans la BD
    }

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

    public float getPrixArticle(String nomArticle)
    {
        float resultat = -1;
        resultat = ReservationFactoryConcrete.get().getArticleBD().getPrix(nomArticle);
        return resultat;
    }

    public int getNumeroReservation(String date, int nTable, String service){
        return 123456;
    }

    public int getNumeroReservation(String date, String nom, String service){
        return 123456;
    }

    public int getNumeroReservation(String nom){
        return 123456;
    }

	public int getNumeroReservation(int numTable){

        if (numTable <= 0) {
            return -1;
        }
        return ReservationFactoryConcrete.get().getTableBD().getNumeroReservation(numTable);
	}

	/**
	 * Donne une string comportant toutes les tables associée à une réservation
	 * Convention : séparer les numeros par des '-'.
	 */
	public LinkedList<Integer> getNumeroTables(int numResa) {
        if (numResa <= 0) {
            return null;
        }
        LinkedList<Integer> tables = ReservationFactoryConcrete.get().getTableBD().getNumeroTable(numResa);
        return tables;
        /*
		String resultat = "";
		try {
			if (tables == null) {
				return resultat;
			}
			// Pas de tuples renvoyé
			if (tables.isEmpty()) {
				return resultat;
			}
			else {
				boolean isFirst=true;
				//while(rset.next()){
                for (Integer t : tables) {
					if(!isFirst){
						resultat += "-";
						isFirst = false;
					}
					resultat += t;
				}
			}
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête getNumeroTable.");
			e.printStackTrace(System.err);
            return null;
		}
        */
	}

	public String getNom(int numResa) {
		String resultat = ReservationFactoryConcrete.get().getTableBD().getNomRes(numResa);
    return resultat;
	}

	public HashMap<String,Integer> getArticlesCommandes(int numResa){
		HashMap<String,Integer> h = ReservationFactoryConcrete.get().getArticleBD().getArticlesCommandes(numResa);
		return h;
	}

	public int getNumResaCmdSelectionne(){
		return numResaCmdSelectionee;
	}

	public int getNumResaSuiviSelectionne(){
		return numResaSuiviSelectionee;
	}

	public void setNumResaCmdSelectionne(int n){
		numResaCmdSelectionee = n;
	}

	public void setNumResaSuiviSelectionne(int n){
		numResaSuiviSelectionee = n;
	}
}
