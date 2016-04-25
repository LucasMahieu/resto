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

	public int getNombreClient() {
		String requete = "SELECT COUNT(*) FROM Client";
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			while (rset.next()) {
				int ret = rset.getInt(1);
				rset.close();
				getStmt().close();
				return ret;
			}
			return -1;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête getNbClient");
			e.printStackTrace(System.err);
			return -1;
		}
	}

	public int exists(String nomClient, String nTel) {
		if (nomClient == null || nTel == null) {
			return -1;
		}
		String requete = new String("SELECT numeroClient FROM Client WHERE ");
		requete += "nomClient = '" + nomClient +"' AND ";
		requete += "telephoneClient = '" + nTel +"'";
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			if (!rset.isBeforeFirst()) {
                rset.close();
                getStmt().close();
				return 0;
			}
			else {
				rset.next();
                int ret = rset.getInt(1);
                rset.close();
                getStmt().close();
                return ret;
            }
		}
		catch (SQLException e) {
			System.err.println("Erreur pour vérifier l'existance d'un client.");
			e.printStackTrace(System.err);
			return -1;
		}
	}

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
