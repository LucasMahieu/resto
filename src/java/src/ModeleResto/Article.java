package ModeleResto;
import java.util.*;
import java.sql.*;

public class Article extends Observable {
    
    private Connection conn;

    public Article(){
    }

    public void setCon(Connection conn) {
        this.conn = conn;
    }

    public ResultSet getArticle(String nomArticle, float prixArticle, String specialite, String type) {
        String requete = new String("SELECT * from article where");
        if (nomArticle != null) {
            requete += ("article.nom = " + nomArticle);
        }
        if (prixArticle != -1) {
            if (nomArticle != null) {	
                requete += " and ";
            }
            requete += (" article.prix == " + prixArticle);
        }
        if (specialite != null) {
            if (nomArticle != null || prixArticle != -1) {	
                requete += " and ";
            }
            requete += ("and article.specialite = " + specialite);
        }
        if (type != null) {
            requete += ("having article.nomarticle in (SELECT * from " + type + " )");
        }	
        requete += ";";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(requete);
            stmt.close();
            return rset;
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    public ResultSet ajoutArticle(String nomArticle, int quantite, int numeroReservation) {
        String requete = new String("Insert into sontcommandes Values");
        requete += ("(" + nomArticle);
        requete += (", " + quantite);
        requete += (", " + numeroReservation);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(requete);
            stmt.close();
            return rset;
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}

/* ATTENTION
 * Il faut fermer les objets Statement et ResultSet et commit !
 */
