package ModeleResto;
//TO DO
import ControleurResto.*;
import java.util.*;
import java.sql.*;

public class Service extends BDitem {

	public Service(){
	}

	public boolean presenceService(String date, String typeService) {

		String requete = new String("SELECT * from Service s where s.typeService='" + typeService + "' AND s.dateService='"+date+"'");

		System.out.println(requete);
		ResultSet rset;
		try {
			setStmt(getCon().createStatement());
			rset = getStmt().executeQuery(requete);
			if(!rset.isBeforeFirst()){
                rset.close();
                getStmt().close();
				return false;
			}
			else {
                rset.close();
                getStmt().close();
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
