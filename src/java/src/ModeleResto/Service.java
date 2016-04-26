package ModeleResto;
//TO DO
import ControleurResto.*;
import java.util.*;
import java.sql.*;

public class Service extends BDitem {

    public Service(){
    }


    /**
     * Existe-t-il un service pour la date donnée? cex : jour férié, dimanche...
     * TODO -> Question : on doit rentrer toutes les dates/services qui existent à la main??
     */
    public boolean presenceService(String date, String typeService) {
	boolean res = false;
	String requete = new String("SELECT * from Service s where s.typeService='" + typeService + "' AND s.dateService='"+date+"'");
	System.out.println(requete);
	try {
	    setStmt(getCon().createStatement());
	    ResultSet rset = getStmt().executeQuery(requete);
	    if(rset.isBeforeFirst()){
		res = true;
	    }
	    rset.close();
	    getStmt().close();
	    return res;
	}
	catch (SQLException e) {
	    System.err.println("Erreur lors de la requête de vérification de la présence d'un serice.");
	    e.printStackTrace(System.err);
	    return false;
	}
    }
}
