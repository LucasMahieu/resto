package ModeleResto;
import java.util.*;
import java.sql.*;
public class Client extends Observable {
    private Connection conn;
    private Statement stmt;

	public Client(){
	}

    public void setCon(Connection conn) {
        this.conn = conn;
    }
	public int getNombreClient(){
		String requete = "SELECT COUNT(*) FROM Client";
		try {
			this.stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(requete);
			while (rset.next()) {
				int ret = rset.getInt(1);
				rset.close();
				stmt.close();
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
			this.stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(requete);
			if (!rset.isBeforeFirst()) {
				return 0;
			}
			else {
				while (rset.next()) {
					int ret = rset.getInt(1);
					rset.close();
					stmt.close();
					return ret;
				}
				return 0;
			}
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête.");
			e.printStackTrace(System.err);
			return -1;
		}
	}

	public int create(String nomClient, String nTel) {
		if (nomClient == null || nTel == null) {
			return -1;
		}
		int lastNb = this.getNombreClient();

		String requete = new String("INSERT INTO Client VALUES (");
		requete += (lastNb + 1) + ", " + nomClient + ", " + nTel + ")";
		try {
			this.stmt = conn.createStatement();
			stmt.executeUpdate(requete);
			stmt.close();
			return lastNb+1;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête.");
			e.printStackTrace(System.err);
			return -1;
		}
	}

}
