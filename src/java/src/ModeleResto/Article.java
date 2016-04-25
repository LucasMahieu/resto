package ModeleResto;
import java.util.*;
import java.sql.*;

public class Article extends BDitem {
    
    public Article(){
    }

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

    public int ajoutArticle(String nomArticle, int quantite, int numeroReservation) {
        if (nomArticle == null || quantite <= 0 || numeroReservation <= 0) {
            return -1;
        }
        String requete = new String("INSERT INTO sontCommandes VALUES");
        requete += "(" + nomArticle;
        requete += ", " + quantite;
        requete += ", " + numeroReservation + ")";

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

    public float getPrix(String nomArticle) {	   	       
        float res = 0;
	if (nomArticle == null) {
	    System.out.println("Le nom de l'article est vide, son prix est donc null");
	    return -1;
	}
	
	String requete = new String("SELECT prixArticle "
				    +"FROM Article "
				    +"WHERE prixArticle = '"+nomArticle+"'"
				    );
        System.out.println(requete);
	try {
	    setStmt(getCon().createStatement());
	    ResultSet rset = getStmt().executeQuery(requete);
	    if (!rset.isBeforeFirst()) {
		return -1;
	    }
            rset.next();
            res = rset.getInt(1);
            rset.close();
            getStmt().close();
	    return res;
	}
	catch (SQLException e) {
	    System.err.println("Erreur pour faire la requête getPrixArticle(article).");
	    e.printStackTrace(System.err);
	    return -1;
	}
    }

    public HashMap<String,Integer> getArticlesCommandes(int numResa) {
	HashMap<String,Integer> h = new HashMap<String,Integer>();
	if (numResa <= 0) {
	    System.out.println("Le numero de reservation est négative");
	    return null;
	}
	String requete = new String("SELECT nomarticle, prixArticle "
				    +"FROM ArticleSontCommandes "
				    +"WHERE numeroReservation = "+numResa
				    );
	System.out.println(requete);
	try {
	    setStmt(getCon().createStatement());
	    ResultSet rset = getStmt().executeQuery(requete);
	    if (!rset.isBeforeFirst()) {
		return null;
	    }
	    String tmpS;
	    int tmpI;
	    while(rset.next()) {
		tmpS = rset.getString(1);
		tmpI = rset.getInt(1);
		h.put(tmpS, tmpI);
	    }
	    rset.close();
	    getStmt().close();
	    return h;
	}
	catch (SQLException e) {
	    System.err.println("Erreur pour faire la requête getArticlecommandes(article).");
	    e.printStackTrace(System.err);
	    return null;
	}
    }
}
