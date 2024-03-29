package ModeleResto;
import java.util.*;
import java.sql.*;

public class Article extends BDitem {

	public Article(){
	}

	/**
	 * Ajoute un article
	 * @param nomArticle nom de d'article
	 * @param quantite quantite
	 * @param numeroReservation numero de la reservation
	 * @return -1 si erreur, 0 sinon
	 */
	public int ajoutArticle(String nomArticle, int quantite, int numeroReservation) {
		if (nomArticle == null || quantite <= 0 || numeroReservation <= 0) {
			return -1;
		}
		int nombreDejaCommande;
		String requete;
		nombreDejaCommande = dejaCommande(nomArticle, numeroReservation);
		if (nombreDejaCommande > 0) {
			requete = new String("UPDATE sontCommandes ");
			requete += "SET quantiteArticle = " + (nombreDejaCommande + quantite) + " ";
			requete += "WHERE nomArticle = '" + nomArticle +"' ";
			requete += "AND numeroReservation = " + numeroReservation;
		}
		else {
			requete = new String("INSERT INTO sontCommandes VALUES");
			requete += "('" + nomArticle;
			requete += "', " + numeroReservation;
			requete += ", " + quantite + ")";
		}

		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			getStmt().executeUpdate(requete);
			getStmt().close();

			setChanged();
			notifyObservers(numeroReservation);
			return 0;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête d'ajout d'article.");
			e.printStackTrace(System.err);
			return -1;
		}
	}

	public String typeArticle(String nomArticle) {
		String ret = new String();

		try {
			ResultSet rsetBoissons = getArticle(nomArticle, -1, null, "BOISSON", null, null);
			ResultSet rsetEntrees = getArticle(nomArticle, -1, null, "ENTREE", null, null);
			ResultSet rsetPlats = getArticle(nomArticle, -1, null, "PLAT", null, null);
			ResultSet rsetDesserts = getArticle(nomArticle, -1, null, "DESSERT", null, null);

			if (rsetBoissons.isBeforeFirst()) {
				return "BOISSON";
			}
			if (rsetEntrees.isBeforeFirst()) {
				return "ENTREE";
			}
			if (rsetPlats.isBeforeFirst()) {
				return "PLAT";
			}
			if (rsetDesserts.isBeforeFirst()) {
				return "DESSERT";
			}
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête de type d'article.");
			e.printStackTrace(System.err);
			return null;
		}
		return null;
	}

	/**
	 * Ajoute un menu dans menucommandes
	 *  @param nomMenu nom du menu
	 *  @param quantite quantite
	 *  @param numeroReservation numero de reservation
	 *  @param nomBoisson nom de la boisson
	 *  @param nomEntree nom de l entree
	 *  @param nomPlat nom du plat
	 *  @param nomDessert nom du dessert
	 *  @return -1 si erreur, 0 sinon
	 */
	public int ajoutMenu(String nomMenu, int quantite, int numeroReservation, String  nomBoisson, String nomEntree, String nomPlat, String nomDessert) {

		if (nomMenu == null || quantite <= 0 || numeroReservation <= 0 || nomPlat == null || (nomBoisson == null && nomEntree == null && nomDessert == null) ) {
			return -1;
		}

		ajoutArticle(nomMenu, quantite, numeroReservation);

		int nombreDejaCommande;
		String requete;

		nombreDejaCommande = dejaCommandeMenuCommandes(nomMenu, numeroReservation, nomBoisson, nomEntree, nomPlat, nomDessert);
		if (nombreDejaCommande > 0) {
			requete = new String("UPDATE MenuCommandes ");
			requete += "SET quantite = " + (nombreDejaCommande + quantite) + " ";
			requete += "WHERE numeroReservation = " + numeroReservation;
			requete += "AND nomMenu = '" + nomMenu +"' ";
			requete += "AND nomBoisson = '" + nomBoisson +"' ";
			requete += "AND nomEntree = '" + nomEntree +"' ";
			requete += "AND nomPlat = '" + nomPlat +"' ";
			requete += "AND nomDessert = '" + nomDessert +"' ";
			requete += "AND quantite = " + nombreDejaCommande;
		}
		else {
			requete = new String("INSERT INTO MenuCommandes VALUES");
			requete += "(" + numeroReservation;
			requete += ", '" + nomMenu +"'";
			requete += ", '" + nomBoisson + "'";
			requete += ", '" + nomEntree + "'";
			requete += ", '" + nomPlat + "'";
			requete += ", '" + nomDessert + "'";
			requete += ", 1)";
		}

		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			getStmt().executeUpdate(requete);
			getStmt().close();

			setChanged();
			notifyObservers(numeroReservation);
			return 0;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête d'ajout de menu.");
			e.printStackTrace(System.err);
			return -1;
		}
	}

	/**
	 * Supprime quantité nomArticle de la reservation n°numeroReservation
	 * @param nomArticle nom de l article
	 * @param quantite quantite
	 * @param numeroReservation numero de la reservation
	 * @return -1 si erreur, 0 sinon
	 */
	public int supprimerArticle(String nomArticle, int quantite, int numeroReservation) {
		if (nomArticle == null || quantite <= 0 || numeroReservation <= 0) {
			return -1;
		}
		int nombreDejaCommande;
		String requete;
		nombreDejaCommande = dejaCommande(nomArticle, numeroReservation);
		if (nombreDejaCommande <= quantite) {
			requete = new String("DELETE FROM sontCommandes ");
			requete += "WHERE nomArticle = '" + nomArticle +"' ";
			requete += "AND numeroReservation = " + numeroReservation;
		}
		else {
			requete = new String("UPDATE sontCommandes ");
			requete += "SET quantiteArticle=" + (nombreDejaCommande-quantite) + " ";
			requete += "WHERE numeroReservation=" + numeroReservation + " ";
			requete += "AND nomArticle='" + nomArticle + "' ";
		}
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			getStmt().executeUpdate(requete);
			getStmt().close();

			setChanged();
			notifyObservers(numeroReservation);
			return 0;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête de suppression d'article.");
			e.printStackTrace(System.err);
			return -1;
		}
	}

	/**
	 * Combien de ce "nomArticle" ont été commandés dans la table sontcommandes
	 * @param nomArticle nom de l article
	 * @param numeroReservation numero de reservation
	 *  @return -1 si erreur, 0 sinon
	 */
	public int dejaCommande(String nomArticle, int numeroReservation) {
		int ret = 0;
		if (nomArticle == null || numeroReservation <= 0) {
			return -1;
		}
		String requete = new String("SELECT quantiteArticle ");
		requete += "FROM sontCommandes ";
		requete += "WHERE nomArticle = '" + nomArticle +"' ";
		requete += "AND numeroReservation = " + numeroReservation;
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			if (rset.next()) {
				ret = rset.getInt(1);
			}
			rset.close();
			getStmt().close();
			return ret;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête dejaCommande.");
			e.printStackTrace(System.err);
			return -1;
		}
	}

	/**
	 * Combien de ce menu ont été commandés (dans Table MenuCommandes)
	 * @param nomMenu nom du menu
	 * @param numeroReservation numero de reservation
	 * @param nomBoisson nom de la boisson
	 * @param nomEntree nom de l entree
	 * @param nomPlat nom du plat
	 * @param nomDessert nom du dessert
	 *  @return -1 si erreur , 0 sinon
	 */
	public int dejaCommandeMenuCommandes(String nomMenu, int numeroReservation, String nomBoisson, String nomEntree, String nomPlat, String nomDessert) {
		if (nomMenu == null || numeroReservation <= 0 ||  nomBoisson == null || nomEntree == null || nomPlat == null || nomDessert == null) {
			return -1;
		}
		int ret = 0;
		String requete = new String("SELECT quantite ");
		requete += "FROM menuCommandes ";
		requete += "WHERE numeroReservation = " + numeroReservation + " ";
		requete += "AND nomMenu = '" + nomMenu +"' ";
		requete += "AND nomBoisson = '" + nomBoisson +"' ";
		requete += "AND nomEntree = '" + nomEntree +"' ";
		requete += "AND nomPlat = '" + nomPlat +"' ";
		requete += "AND nomDessert = '" + nomDessert +"' ";
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			if (rset.next()) {
				ret = rset.getInt(1);
			}
			rset.close();
			getStmt().close();
			return ret;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête dejaCommandeMenu.");
			e.printStackTrace(System.err);
			return -1;
		}
	}

	/**
	 * Retourne toutes les informations sur l'article en question
	 * @param nomArticle nom de l article
	 * @param prixArticle prix de l article
	 * @param specialite specialite
	 * @param type type d article
	 * @return resultat de la requete
	 */
	public ResultSet getArticle(String nomArticle, float prixArticle, String specialite, String type, String date, String service) {
		String requete = new String("SELECT * FROM Article");
		if (date != null && service == null) {
			requete += ", Disponibles ";
		}
		if (nomArticle != null || prixArticle != -1 || specialite != null) {
			requete += " WHERE ";
		}
		if (nomArticle != null) {
			requete += ("Article.nomArticle = '" + nomArticle + "'");
		}
		if (prixArticle != -1) {
			if (nomArticle != null) {
				requete += " AND ";
			}
			requete += ("Article.prixArticle = " + prixArticle);
		}
		if (specialite != null) {
			if (nomArticle != null || prixArticle != -1) {
				requete += " AND ";
			}
			requete += ("AND Article.specialite = '" + specialite + "' ");
		}

		if (date != null && service != null) {
			requete += " AND Article.nomArticle = Disponibles.nomArticle AND (";
			LinkedList<String> cartes = getCarte(date, service);
			if (cartes == null) {
				return null;
			}
			for (String carte : cartes) {
				requete += "Disponibles.nomCarte = '" + carte + "' or ";
			}
			requete = requete.substring(0, requete.length()-3);
			requete += ") ";
		}
		if (type != null) {
			requete += (" GROUP BY nomArticle, specialite, prixArticle HAVING Article.nomArticle IN ");
			if (type == "menu") {
				requete += " (SELECT Menu.nomMenu FROM " + type + ")";
			} else {
				requete += " (SELECT * FROM " + type + ")";
			}
		}
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			return rset;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la getArticle.");
			e.printStackTrace(System.err);
			return null;
		}
	}

	/**
	 * Retourne la liste des cartes disponibles pour le jour et le service choisis
	 */
	public LinkedList<String> getCarte(String date, String service) {
		LinkedList<String> res = new LinkedList<String>();
		String requete;
		if (date == null || service == null) {
			return null;
		}
		requete = new String("SELECT nomCarte FROM Service");
		requete += " WHERE Service.dateService ='" + date + "' ";
		requete += "AND Service.typeService = '"+ service + "'";

		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			while (rset.next()) {
				res.add(rset.getString(1));
			}
			rset.close();
			getStmt().close();
			return res;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la getArticleMenu");
			e.printStackTrace(System.err);
			return null;
		}
	}

	/**
	 * Retourne les articles d'un certain type qui sont disponibles pour un menu donné 
	 * //TODO, marche pas ici
	 * @param nomMenu nom du menu
	 * @param type type d article
	 * @return liste d article du type demande
	 */
	public LinkedList<String> getArticleMenu(String nomMenu, String type, String date, String service) {
		LinkedList<String> res = new LinkedList<String>();
		String requete;
		if (nomMenu == null || type == null) {
			return null;
		}
		//if (!getCarte(date, service).contains(nomMenu)) {
		//	return null;
		//}
		if (type == "Plat") {
			res.add(getArticleMenuBis(nomMenu, type));
		}
		//Puis on cherche dans la table estcompose, quel que soit le type
		requete = new String("SELECT e.nomChoix FROM estCompose e, " + type);
		requete += " WHERE e.nomMenu ='" + nomMenu + "' ";
		requete += "AND "+type+".nom"+type + "= e.nomChoix";

		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			while (rset.next()) {
				res.add(rset.getString(1));
			}
			rset.close();
			getStmt().close();
			return res;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la getArticleMenu");
			e.printStackTrace(System.err);
			return null;
		}
	}

	/**
	 * Est utilisé dans la fonction principale
	 * @param nomMenu nom du menu
	 * @param type type d article
	 * @return nom d article
	 *
	 */
	public String getArticleMenuBis(String nomMenu, String type) {
		String  res = null;
		String requete;
		if (nomMenu == null || type == null) {
			return null;
		}
		//On cherche d'abord le plat auquel est associé le menu dans la table menu
		requete = new String("SELECT nomPlatBase FROM Menu");
		requete += "WHERE nomMenu = '" + nomMenu + "'";
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			while (rset.next()) {
				res = new String(rset.getString(1));
			}
			rset.close();
			getStmt().close();
			return res;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la getArticleMenu");
			e.printStackTrace(System.err);
			return null;
		}
	}


	/**
	 * Retourne les articles commandés pour une reservation pour une etape donnée (on ne renvoie pas le contenu des menus)
	 * @param numRes numero de la reservation
	 * @param etape etape du repas
	 * @return HashMap des articles commandes
	 */
	public HashMap<String, Integer> getArticlesCommandes(int numRes, String etape) {
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		if (numRes <= 0) {
			return res;
		}
		String requete = new String("SELECT nomArticle, quantiteArticle FROM sontCommandes");
		requete += ", " + etape;
		requete += " WHERE numeroReservation = " + numRes;
		requete += " AND sontcommandes.nomArticle = " + etape + ".nom"+etape;
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			while (rset.next()) {
				res.put(rset.getString(1), rset.getInt(2));
			}
			rset.close();
			getStmt().close();
			return res;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la getArticlesCommandes");
			e.printStackTrace(System.err);
			return null;
		}
	}


	/**
	 * Retourne les articles commandés pour une reservation dans tous les menus (dans menuCommandes)
	 * @param numRes numero de reservation
	 * @return liste des articles commandes dans les menus
	 */
	public LinkedList<String> getArticlesMenuCommandes(int numRes) {
		LinkedList<String> res = new LinkedList<String>();
		if (numRes <= 0) {
			return res;
		}
		String requete = new String("SELECT nomBoisson, nomEntree, nomPlat, nomDessert FROM menuCommandes ");
		requete += "WHERE numeroReservation = " + numRes;
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			while (rset.next()) {
				res.add(rset.getString(1));
			}
			rset.close();
			getStmt().close();
			return res;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête getArticlesMenuCommandes.");
			e.printStackTrace(System.err);
			return null;
		}
	}

	/**
	 * Retourne les articles commandés pour une reservation dans tous les menus (dans menuCommandes) DANS UNE ETAPE
	 * @param numRes numero de reservation
	 * @param type etape du repas
	 * @return liste des articles d'une etape du menu
	 */
	public LinkedList<String> getArticlesMenuCommandesType(int numRes, String type) {
		LinkedList<String> res = new LinkedList<String>();
		if (numRes <= 0) {
			return res;
		}
		String requete = new String("SELECT nom"+type+" FROM menuCommandes");
		requete += "WHERE numeroReservation = " + numRes;
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			while (rset.next()) {
				res.add(rset.getString(1));
			}
			rset.close();
			getStmt().close();
			return res;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête getArticlesMenuCommandes.");
			e.printStackTrace(System.err);
			return null;
		}
	}
	/**
	 * Supprime quantité nomMenu de la reservation n°numeroReservation
	 * @param nomMenu nom du menu
	 * @param quantite quantite
	 * @param numeroReservation numero de reservation
	 * @param nomBoisson nom de la boisson
	 * @param nomEntree nom de l entree
	 * @param nomPlat nom du plat
	 * @param nomDessert nom du dessert
	 * @return -1 si erreur, 0 sinon
	 */
	public int supprimerMenu(String nomMenu, int quantite, int numeroReservation, String nomBoisson, String nomEntree, String nomPlat, String nomDessert) {
		if (numeroReservation <= 0 || nomMenu == null || nomBoisson == null || nomEntree == null || nomPlat == null || nomDessert == null) {
			return -1;
		}
		supprimerArticle(nomMenu, quantite, numeroReservation);

		int ret = -1;
		int nombreDejaCommandeMenu = dejaCommandeMenuCommandes(nomMenu, numeroReservation, nomBoisson, nomEntree, nomPlat, nomDessert);
		String requete;
		if (nombreDejaCommandeMenu <= quantite) {
			requete = new String("DELETE FROM MenuCommandes ");
			requete += "WHERE nomMenu =  '" + nomMenu +"' ";
			requete += "AND nomMenu = '" + nomMenu +"' ";
			requete += "AND nomBoisson = '" + nomBoisson +"' ";
			requete += "AND nomEntree = '" + nomEntree +"' ";
			requete += "AND nomPlat = '" + nomPlat +"' ";
			requete += "AND nomDessert = '" + nomDessert +"' ";
		}
		else {
			requete = new String("UPDATE MenuCommandes ");
			requete += "SET quantite = " + (nombreDejaCommandeMenu - quantite) + " ";
			requete += "WHERE nomMenu =  '" + nomMenu +"' ";
			requete += "AND nomMenu = '" + nomMenu +"' ";
			requete += "AND nomBoisson = '" + nomBoisson +"' ";
			requete += "AND nomEntree = '" + nomEntree +"' ";
			requete += "AND nomPlat = '" + nomPlat +"' ";
			requete += "AND nomDessert = '" + nomDessert +"' ";
		}
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			getStmt().executeUpdate(requete);
			getStmt().close();
			
			setChanged();
			notifyObservers();
			return 0;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête de suppression d'article.");
			e.printStackTrace(System.err);
			return -1;
		}
	}

	/**
	 * Retourne le prix de l'aticle demandé
	 * @param nomArticle nom de l article
	 * @return prix de l article en euros
	 */
	public float getPrix(String nomArticle) {
		float res = -1;
		if (nomArticle == null) {
			return -1;
		}
		String requete = new String("SELECT prixArticle "
				+"FROM Article "
				+"WHERE nomArticle = '"+nomArticle+"'"
				);
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			if (rset.next()) {
				res = rset.getInt(1);
			}
			rset.close();
			getStmt().close();
			return res;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête.");
			e.printStackTrace(System.err);
			return -1;
		}
	}
}
