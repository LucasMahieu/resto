package ModeleResto;
import java.util.*;
import java.sql.*;

public class Client extends BDitem {

    public Client() {
    }

    /**
     * Retourne le nombre de clients
     *  @return -1 si erreur, 0 sinon
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
     * @param nomClient nom du client
     * @param nTel numero de telephone
     *  @return -1 si erreur, 0 sinon
     */
    public int existsClient(String nomClient, String nTel) {
     // TODO rajout du count(*) nécessaire ? Si non, modification dans ajoutclient?
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
     * @param nomClient nom du client
     * @param nTel numero de telephone
     * @return -1 si erreur, numero du client sinon
     */
    public int create(String nomClient, String nTel) {
     // TODO -> ++lastClient, ca marche vraiment?
	if (nomClient == null || nTel == null) {
	    return -1;
	}
    int lastClient = getNombreClient();

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
