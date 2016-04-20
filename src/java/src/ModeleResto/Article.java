package ModeleResto;
import java.util.*;
import java.sql.*;

public class Article extends Observable {
    
    private Connection conn;
    // Transaction actuelle
    private Statement stmt;

    public Article(){
    }

    public void setCon(Connection conn) {
        this.conn = conn;
    }

    public Statement getStmt() {
        return this.stmt;
    }

    public ResultSet getArticle(String nomArticle, float prixArticle, String specialite, String type) {
        String requete = new String("SELECT * FROM Article WHERE ");
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
            requete += ("HAVING Article.nomArticle in (SELECT * FROM " + type + ")");
        }	
        
        System.out.println(requete);
        try {
            this.stmt = conn.createStatement();
            //ResultSet rset = stmt.executeQuery(requete);
            ResultSet rset = stmt.executeQuery("SELECT * FROM Article");
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
            this.stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(requete);
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
