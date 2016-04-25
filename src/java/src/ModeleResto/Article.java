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
}
