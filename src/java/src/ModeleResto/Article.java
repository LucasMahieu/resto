package ModeleResto;
import java.util.*;
import java.sql.*;

public class Article extends BDitem {

	public Article(){
	}

	/**
	 * Ajoute un article
	 * -1 -> Erreur
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
			return 0;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête d'ajout d'article."); 
			e.printStackTrace(System.err);
			return -1;
		}
	}

	/**
	 * Supprime quantité nomArticle de la reservation n°numeroReservation
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
			return 0;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête de suppression d'article."); 
			e.printStackTrace(System.err);
			return -1;
		}
	}

	/**
	 * Combien de ce "nomArticle" ont été commandés
	 * -1 -> Erreur
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
			System.err.println("Erreur pour faire la requête d'article."); 
			e.printStackTrace(System.err);
			return -1;
		}
	}

	/**
	 * Retourne toutes les informations sur l'article en question
	 */

	public ResultSet getArticle(String nomArticle, float prixArticle, String specialite, String type) {
		String requete = new String("SELECT * FROM Article ");
		if (nomArticle != null || prixArticle != -1 || specialite != null) {
			requete += "WHERE ";
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
			requete += ("AND Article.specialite = '" + specialite + "'");
		}
		if (type != null) {
			requete += ("GROUP BY nomArticle, specialite, prixArticle HAVING Article.nomArticle IN ");
			if (type == "menu") {
				requete += "(SELECT Menu.nomMenu FROM " + type + ")";
			}
			else {
				requete += "(SELECT * FROM " + type + ")";
			}
		}
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			return rset;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête.");
			e.printStackTrace(System.err);
			return null;
		}
	}
	/**
	 * Retourne les articles commandés pour une reservation
	 */
	public HashMap<String, Integer> getArticlesCommandes(int numRes) {
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		if (numRes <= 0) {
			return res;
		}
		String requete = new String("SELECT nomArticle, quantiteArticle FROM sontCommandes ");
		requete += "WHERE numeroReservation = " + numRes;

		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			while (rset.next()) {
				res.put(rset.getString(1), rset.getInt(2));
			}
			return res;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête.");
			e.printStackTrace(System.err);
			return null;
		}
	}
	/**
	 * Retourne le prix de l'aticle demandé
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
