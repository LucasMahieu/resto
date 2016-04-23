package ModeleResto;
import java.util.*;
import java.sql.*;

public class Article extends Observable {
    
    private Connection conn;
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
            this.stmt = conn.createStatement();
			stmt.executeUpdate(requete);
            stmt.close();
            return 0;
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête d'ajout d'article.");
            e.printStackTrace(System.err);
            return -1;
        }
    }
}
