package ModeleResto;
import java.util.*;
import java.sql.*;

public class Client extends BDitem {

    // Numéro client du dernier client ajouté.
    // On en a déja 3.
    private int lastClient = 0;

    public Client() {
    }

    public void setLastClient(int lC) {
        this.lastClient = lC;
    }

    public int getLastClient() {
        return this.lastClient;
    }

    /**
     * Retourne le nombre de clients
     * -1 -> Erreur
     */
    public int getNombreClient() {
	int ret = 0;
	String requete = "SELECT COUNT(*) FROM Client";
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
	    System.err.println("Erreur pour faire la requête.");
	    e.printStackTrace(System.err);
	    return -1;
	}
    }

    /**
     * Retourne le numeroClient s'il existe
     * -1 -> Erreur
     *  0 -> Absent
     * TODO rajout du count(*) nécessaire ? Si non, modification dans ajoutclient?
     */
    public int existsClient(String nomClient, String nTel) {
	int res = 0;
	if (nomClient == null || nTel == null) {
	    return -1;
	}
	String requete = new String("SELECT numeroClient FROM Client WHERE ");
	requete += "nomClient = '" + nomClient +"' AND ";
	requete += "telephoneClient = '" + nTel +"'";
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
	    System.err.println("Erreur pour vérifier l'existence d'un client.");
	    e.printStackTrace(System.err);
	    return -1;
	}
    }

    /**
     * Crée un client
     * -1 -> Erreur
     * sinon, numero du client
     * TODO -> ++lastClient, ca marche vraiment?
     */
    
    public int create(String nomClient, String nTel) {
	if (nomClient == null || nTel == null) {
	    return -1;
	}

	String requete = new String("INSERT INTO Client VALUES (");
	requete += (lastClient + 1) + ", '" + nomClient + "', '" + nTel + "')";
        System.out.println(requete);
	try {
	    setStmt(getCon().createStatement());
	    getStmt().executeUpdate(requete);
	    getStmt().close();
	    return ++lastClient;
	}
	catch (SQLException e) {
	    System.err.println("Erreur pour faire la création d'un client.");
	    e.printStackTrace(System.err);
	    return -1;
	}
    }

}
