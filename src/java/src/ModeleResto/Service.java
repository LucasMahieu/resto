package ModeleResto;
//TO DO
import ControleurResto.*;
import java.util.*;
import java.sql.*;

public class Service extends Observable {
	private Connection conn;
	private Statement stmt;

	public Service(){
	}

	public void setCon(Connection conn) {
		this.conn = conn;
	}
	public Statement getStmt() {
		return this.stmt;
	}

	public boolean presenceService(String date, String typeService) {

		String requete = new String("SELECT * from Service s where s.typeService='" + typeService + "' AND s.dateService='"+date+"'");

		System.out.println(requete);
		ResultSet rset;
		try {
			this.stmt = conn.createStatement();
			rset = stmt.executeQuery(requete);
			if(!rset.isBeforeFirst()){
                rset.close();
                stmt.close();
				return false;
			}
			else {
                rset.close();
                stmt.close();
				return true;
			}
		}
		catch (SQLException e) {
			System.err.println("Erreur lors de la requête de vérification de la présence d'un serice.");
			e.printStackTrace(System.err);
			return false;
		}
	}
}
