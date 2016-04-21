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
 */
public class Controleur{
    private static int numResaCmdSelectionee;
    private static int numResaSuiviSelectionee;
    private static Date date;
    private static final SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat sdfHeure = new SimpleDateFormat("HH");
    private static String dateNow;
    private static String heureNow;
    private static String serviceNow;
    private static final int DEBUT_SERVICE_SOIR = 17;

    final private static Controleur instanceUnique = new Controleur();

    /* à modifier pour le patron Singleton */
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
	public void ajouterArticle(String nom, int quantite, int numResa){
		// ajouter à la resa l'article donner avec les bonnes quantités dans la BD
	}
	public void creerFacture(String client){
       // Facture factureFinale = new Facture();
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
        serviceNow = "midi";
        if(Integer.parseInt(getHeureNow()) >= DEBUT_SERVICE_SOIR){
            serviceNow = "soir";
        }
        return this.serviceNow;
    }

    public static int creerReservation(String nom, String date, String service,int nbPersonnes, String localisation, String tel){
        //Vérification des disponibilités des tables
        //Appel à la création de réservation dans la BD
		String tables = "";
		//tables = "10-11-12";
		ArrayList<Integer> tablesArray = trouverTable(localisation, date, service, nbPersonnes);
		boolean isFirst = true;
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
		for(int i=0; i<tablesArray.size(); i++){
			if(!isFirst){
				tables+="-";
			}
			tables+=tablesArray.get(i).toString();
		}
		int tmp = ReservationFactoryConcrete.creerReservation(nom, date, service, nbPersonnes, localisation, tel, tables);
		if(tmp<0){
			//erreur
			System.out.println("erreur creation resa");
			return -1;
		}
		if(tmp==0){
			System.out.println("probleme creation resa");
			return tmp;
		}
		numResaSuiviSelectionee = tmp;
		numResaCmdSelectionee = tmp; 
		return tmp;
        //return 14;
    }

	public static ArrayList<Integer> trouverTable(String localisation, String date, String service, int nbPersonnes){
		ArrayList<Integer> res = new ArrayList<Integer>();
		ArrayList<Integer> table = new ArrayList<Integer>();
		try {
            ResultSet rset = ReservationFactoryConcrete.get().getTableBD().getTableLibre(localisation, date, service);
            if (rset == null) {
				//erreur à gerer
                return null;
            }
            while(rset.next()){
                res.add(rset.getInt(1));
            }
            rset.close();
            ReservationFactoryConcrete.get().getTableBD().getStmt().close();
			// Res contient toutes les tables libres pour ce jour, service , localisation
			int nbPlace = 0;
			int resteMin = 1000;
			int config = 0;
			int tableIdeal = 0;
			for(Integer i : res){
				nbPlace = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(i,config);
				ReservationFactoryConcrete.get().getTableBD().getStmt().close();
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
			//Sinon on essaye d'accoler une table
			config++;
			int tableVoisine[] = {0,0};
			int nbPlaceVoisine[] = {0,0};
			int j=0;
			int tableIdealAccolee[] = {0,0};
			resteMin = 1000;
			for(Integer i : res){
				nbPlace = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(i,config);
				ReservationFactoryConcrete.get().getTableBD().getStmt().close();

				rset = ReservationFactoryConcrete.get().getTableBD().getTableVoisine(i);
				if (!rset.isBeforeFirst()) {
					ReservationFactoryConcrete.get().getTableBD().getStmt().close();
					continue;
				}else{
					j=0;
					while(rset.next()){
						tableVoisine[j] = rset.getInt(1);
						j++;
					}
				}
				ReservationFactoryConcrete.get().getTableBD().getStmt().close();
				nbPlaceVoisine[0] = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(tableVoisine[0],config);
				ReservationFactoryConcrete.get().getTableBD().getStmt().close();
				nbPlaceVoisine[1] = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(tableVoisine[1],config);
				ReservationFactoryConcrete.get().getTableBD().getStmt().close();
				// on a donc le nombre de place accolée1 des deux voisines 
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
			if(tableIdealAccolee[0]!=0 && tableIdealAccolee[1]!=0){
				table.add(tableIdealAccolee[0]);
				table.add(tableIdealAccolee[1]);
				return table;
			}
			//Sinon on essaye d'accoler deux table
			config++;
			j=0;
			int tableIdealAccolee2[] = {0,0,0};
			resteMin = 1000;
			for(Integer i : res){
				// Table i
				nbPlace = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(i,config);
				ReservationFactoryConcrete.get().getTableBD().getStmt().close();
				// les tables voisines de i
				rset = ReservationFactoryConcrete.get().getTableBD().getTableVoisine(i);
				if (!rset.isBeforeFirst()) {
					// Pas de voisin, on passe à i++
					ReservationFactoryConcrete.get().getTableBD().getStmt().close();
					continue;
				}else{
					j=0;
					while(rset.next()){
						tableVoisine[j] = rset.getInt(1);
						j++;
					}
				}
				//On recupere le nombre de places des 2 voisins pour un accolement à i
				//ATENTION les tables voisines vont etre en accolement simple
				//seul i est en accolement double -> on utilise config-1 pour les voisines
				ReservationFactoryConcrete.get().getTableBD().getStmt().close();
				nbPlaceVoisine[0] = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(tableVoisine[0],config-1);
				ReservationFactoryConcrete.get().getTableBD().getStmt().close();
				nbPlaceVoisine[1] = ReservationFactoryConcrete.get().getTableBD().nbPlaceTable(tableVoisine[1],config-1);
				ReservationFactoryConcrete.get().getTableBD().getStmt().close();
				// on a donc le nombre de place accolée1 des deux voisines 
				if(nbPlace+nbPlaceVoisine[0]+nbPlaceVoisine[1]>=nbPersonnes){
					if(nbPlace+nbPlaceVoisine[0]+nbPlaceVoisine[1]-nbPersonnes<resteMin){
						resteMin = nbPlace+nbPlaceVoisine[0]+nbPlaceVoisine[1] - nbPersonnes;
						tableIdealAccolee2[0] = i;
						tableIdealAccolee2[1] = tableVoisine[0];
						tableIdealAccolee2[2] = tableVoisine[1];
					}
				}
			}
			if(tableIdealAccolee2[0]!=0 && tableIdealAccolee2[1]!=0){
				table.add(tableIdealAccolee2[0]);
				table.add(tableIdealAccolee2[1]);
				table.add(tableIdealAccolee2[3]);
				return table;
			}
			// Si on arrive la, c'est qu'il y a pas de table assez grande
			// dans la localisation donnée Donc on fait la recherche dans les autres loc
			if(localisation!=null){
				table = trouverTable(null,date,service,nbPersonnes);
			}
			return table;
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête.");
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

    public static void supprimerReservation(String nom, String prenom, int nbPersonnes, String date, String service, String localisation){
        //Vérification de l'existence de la réservation 
        //si ok:
        //Appel à la suppression de réservation dans la BD
    }

    public static  LinkedList<String> getListeArticles(String type)
    {
        LinkedList<String> resultat = new LinkedList<String>();
        /*
        if (type == "boisson"){
            resultat.add("Fanta");
            resultat.add("Vin-chaud");
            resultat.add("Coca");
            resultat.add("Vin-Blanc");
            resultat.add("perrier");
            resultat.add("champagneeee");
            resultat.add("Limonade");
            resultat.add("Duvel");
            resultat.add("Hougarden");
            resultat.add("Vodka");
            resultat.add("Wisky");
            resultat.add("Grenadine");
            resultat.add("Lait-fraise");
            resultat.add("Jus de papaye");
            resultat.add("eau de vie");
            resultat.add("sirop de caoutchou");
            resultat.add("infusion de chaussettes");
        }else if (type=="entree"){
            resultat.add("salade");
            resultat.add("chevre chaud");
            resultat.add("foie gras");
            resultat.add("charcuterie");
            resultat.add("surimi");
            resultat.add("bouillabesse");
            resultat.add("os à moelle");
            resultat.add("oeuf de caille");
            resultat.add("frillant au bleu");
            resultat.add("taboulet");
        }else if(type=="plat"){
            resultat.add("langue de boeuf");
            resultat.add("knaki");
            resultat.add("poulet rotis");
            resultat.add("couscous");
            resultat.add("raclette");
            resultat.add("fondu");
            resultat.add("plat du jour");
            resultat.add("steak frite");
            resultat.add("bolognaire");
            resultat.add("carbonara");
            resultat.add("sanglier mariné");
            resultat.add("poulpe");
            resultat.add("choux farcie");
            resultat.add("rognon");
            resultat.add("choucroutte");
            resultat.add("cassoulet");
            resultat.add("pizza");
        }else if (type=="dessert"){
            resultat.add("Ils-flottante");
            resultat.add("yaourt");
            resultat.add("glace");
            resultat.add("fruit");
            resultat.add("gaateau");
            resultat.add("chaucolot");
        }else if (type=="menu"){
            resultat.add("Chef");
            resultat.add("Maitre");
            resultat.add("Tourista");
        }
        */
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

    public static float getPrixArticle(String nomArticle)
    {
        //ResultSet rset = getArticles(nomArticle, -1, null, null);
        //rset.getFloat(2);

        //rset.close();
        //return resultat;
        return (float)10.0;
    }

    public static  int getNumeroReservation(String date, int nTable, String service){
        return 123456;
    }
    public static int getNumeroReservation(String date, String nom, String service){
        return 123456;
    }
    public static int getNumeroReservation(String nom){
        return 123456;
    }
	public static int getNumeroReservation(int numTable){
		int resultat = 0;
		try {
			ResultSet rset = ReservationFactoryConcrete.get().getTableBD().getNumeroReservation(numTable);
			if (rset == null) {
				return resultat;
			}
			// Pas de tuples renvoyé
			if (!rset.isBeforeFirst()) {
				return resultat;
			}
			else {
				while(rset.next()){
					resultat = rset.getInt(1);
				}
			}
			rset.close();
			ReservationFactoryConcrete.get().getTableBD().getStmt().close();
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête getNumeroTable.");
			e.printStackTrace(System.err);
			resultat = -1;
		}
		return resultat;

	}
	/**
	 * Donne une string comportant toutes les tables associée à une réservation
	 * Convention : séparer les numeros par des '-'.
	 */
	public static String getNumeroTables(int numResa){
		String resultat = "";
		try {
			ResultSet rset = ReservationFactoryConcrete.get().getTableBD().getNumeroTable(numResa);
			if (rset == null) {
				return resultat;
			}
			// Pas de tuples renvoyé
			if (!rset.isBeforeFirst()) {
				return resultat;
			}
			else {
				boolean isFirst=true;
				while(rset.next()){
					if(!isFirst){
						resultat += "-";
						isFirst = false;
					}
					resultat += rset.getString(1);
				}
			}
			rset.close();
			ReservationFactoryConcrete.get().getTableBD().getStmt().close();
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête getNumeroTable.");
			e.printStackTrace(System.err);
		}
		return resultat;
	}
	public String getNom(int numResa) {
		return "M. Dieudo";
	}

	public static HashMap<String,Integer> getArticlesCommandes(int numResa){
		HashMap<String,Integer> h = new HashMap<String,Integer>();
		h.put("Menu du roi",1);
		h.put("Quenelles Farcies", 2);
		h.put("Hugarden", 100);
		h.put("Menu Tourista",1);
		h.put("Frite",7);
		return h;
	}

	public static int getNumResaCmdSelectionne(){
		return numResaCmdSelectionee;
	}
	public static int getNumResaSuiviSelectionne(){
		return numResaSuiviSelectionee;
	}
	public static void setNumResaCmdSelectionne(int n){
		numResaCmdSelectionee = n;
	}
	public static void setNumResaSuiviSelectionne(int n){
		numResaSuiviSelectionee = n;
	}
}
