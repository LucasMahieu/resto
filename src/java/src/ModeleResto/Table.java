package ModeleResto;
//TO DO
import ControleurResto.*;
import java.util.*;
import java.sql.*;

public class Table extends BDitem {

    public Table(){
    }

    /**
     * Donne les tables libre d'une localisation pour une date et un service
     * ou toutes les tables libres si la localisation n'est pas précisée
     */
    public LinkedList<Integer> getTableLibre(String loc, String date, String service) {
        int t = 0;
        LinkedList<Integer> res = new LinkedList<Integer>();
        if (date == null || service == null) {
            return null;
        }
        String requete = new String(
                "SELECT t.numeroTable "
                +"FROM tables t ");
        if(loc!=null && loc != ""){
            requete+="WHERE t.localisation='"+loc+"' ";
        }
        requete+="MINUS "
            +"SELECT er.numerotable "
            +"FROM estreservee er, reservation r "
            +"WHERE er.numeroreservation = r.numeroreservation "
            +"AND r.dateService='"+date+"' "
            +"AND r.typeService='"+service+"' "
            ;
        System.out.println(requete);
        try {
            setStmt(getCon().createStatement());
            ResultSet rset = getStmt().executeQuery(requete);
            while(rset.next()) {
                res.add(rset.getInt(1));
            }
            rset.close();
            this.getStmt().close();
            return res;
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête tableLibre:");
            e.printStackTrace(System.err);
            return null;
        }
    }
    /**
     * Donne le nombre de places restantes d'une table donnée.
     * @param tab numero de la table 
     * @param config 0 pour table isolée, 1 pour accolée à 1 table, 2 pour accolée à 2 tables voisines
     */
    public int nbPlaceTable(int tab, int config) {
        if (tab <= 0) {
            return -1;
        }
        int ret=0;
        String requete = new String("SELECT ");
        if(config == 0){
            requete += "nombrePlaceIsolee ";
        }else if(config == 1){
            requete += "nombrePlaceAccolee1 ";
        }else if(config == 2){
            requete += "nombrePlaceAccolee2 ";
        }else {
            return -1;
        }
        requete +="FROM Tables t "
            +"WHERE t.numeroTable="+tab;
        System.out.println(requete);
        try {
            setStmt(getCon().createStatement());
            ResultSet rset = getStmt().executeQuery(requete);
            if (!rset.isBeforeFirst()) {
                rset.close();
                this.getStmt().close();
                return -1;
            }
            else {
                int i = 0;
                rset.next();
                i = rset.getInt(1);
                rset.close();
                this.getStmt().close();
                return i;
            }
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête nbPlaceTable.");
            e.printStackTrace(System.err);
            return -1;
        }
    }

    /**
     * Retourne la liste des tables voisine à une table donnée
     * MARCHE SUR LA BD
     */
    public LinkedList<Integer> getTableVoisine(int tab){
        LinkedList<Integer> res = new LinkedList<Integer>();
        if (tab <= 0) {
            return null;
        }
        String requete = new String("SELECT "
                +"sv.numerotable2 "
                +"FROM sontvoisines sv "
                +"WHERE sv.numerotable1="+tab
                );
        System.out.println(requete);
        try {
            setStmt(getCon().createStatement());
            ResultSet rset = getStmt().executeQuery(requete);
            while (rset.next()) {
                res.add(rset.getInt(1));
            }
            rset.close();
            this.getStmt().close();
            return res;
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête getTableVoisin.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    /*
     * Donne les numeros de table d'une resa
     */
    public LinkedList<Integer> getNumeroTable(int numResa) {
        LinkedList<Integer> res = new LinkedList<Integer>();
        if (numResa <= 0) {
            return null;
        }
        String requete = new String("SELECT "
                +"er.numerotable "
                +"FROM estreservee er "
                +"WHERE er.numeroreservation="+numResa
                );
        System.out.println(requete);
        try {
            setStmt(getCon().createStatement());
            ResultSet rset = getStmt().executeQuery(requete);
            while (rset.next()) {
                res.add(rset.getInt(1));
            }
            rset.close();
            this.getStmt().close();
            return res;
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête getNumeroTable.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    /**
     * Donne le numéro de resa pour une table donnée
     * avec une date et un service.
     */
    public int getNumeroReservation(int numTable, String date, String service) {
        int res = 0;
        if (numTable <= 0 || date == null || service == null) {
            return -1;
        }
        String requete = new String("SELECT estReservee.numeroReservation ");
        requete += "FROM estReservee, Reservation ";
        requete += "WHERE estReservee.numeroReservation = Reservation.numeroReservation ";
        requete += "AND numeroTable = " + numTable + " ";
        requete += "AND dateService = '" + date + "' ";
        requete += "AND typeService = '" + service + "'";

        System.out.println(requete);
        try {
            setStmt(getCon().createStatement());
            ResultSet rset = getStmt().executeQuery(requete);
            if (rset.next()) {
                res = rset.getInt(1);
            }
            rset.close();
            this.getStmt().close();
            return res;
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête getNumeroReservation(table).");
            e.printStackTrace(System.err);
            return -1;
        }
    }

    /**
     * Ajoute une table à une réservation.
     */
    public int ajouterTable(int numeroTable, int numeroReservation) {
        if (numeroTable <= 0 || numeroReservation <= 0) {
            return -1;
        }
        String requete = new String("INSERT INTO estReservee VALUES (");
        requete += numeroTable + ", " + numeroReservation + ")";
        System.out.println(requete);
        try {
            setStmt(getCon().createStatement());
            getStmt().executeUpdate(requete);
            getStmt().close();
            return 0;
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête.");
            e.printStackTrace(System.err);
            return -1;
        }
    }

    public String getNomRes(int numRes) {
        String res;
        if (numRes <= 0) {
            return null;
        }      
        String requete = new String("SELECT "
                +"Client.nomclient "
                +"FROM Reservation, Client "
                +"WHERE Client.numeroclient = Reservation.numeroclient "
                +"AND reservation.numeroreservation = " + numRes
                );
        System.out.println(requete);
        try {
            setStmt(getCon().createStatement());
            ResultSet rset = getStmt().executeQuery(requete);
            if (!rset.isBeforeFirst()) {
                return null;
            }
            rset.next();
            res = new String(rset.getString(1));
            rset.close();
            getStmt().close();
            return res;
        }
        catch (SQLException e) {
            System.err.println("Erreur pour faire la requête getNom(table).");
            e.printStackTrace(System.err);
            return null;
        }
    }

    public void supprimerReservation(int numeroTable, String date, String service) {
	int res = 0;
	if (numeroTable <= 0) {
	    System.out.println("Numero de Table négatif dans existsReservation"); //TODO peut etre lancer une erreur
	    return;
	}
	String requete = new String("DELETE FROM Reservation" //supprimer la classe reservation que a été créée?
				    + "FROM estReserve, Reservation"
				    + "Where Reservation.date = '"+date+"' "
				    + "AND Reservation.service = "+service+"' "
				    + "Reservation.numeroreservation = estReserve.numeroreservation"
				    + "estReserve.numerotable ="+ numeroTable);
	System.out.println(requete);
	try {
	    setStmt(getCon().createStatement());
	    getStmt().executeUpdate(requete);
	    getStmt().close();
	    return;
	}
	catch (SQLException e) {
	    System.err.println("Erreur pour faire la requête supprimerReservation(table).");
	    e.printStackTrace(System.err);
	    return;
	}
    }
    

//probleme -> utiliser exists, il peut y avoir plusieurs résultats et c est pas vérifié
    public boolean existsReservation(int numeroTable, String date, String service) {
	if (numeroTable <= 0) {
	    System.out.println("Numero de Table négatif dans existsReservation"); //TODO peut etre lancer une erreur
	    return false;
	}
	String requete = new String("SELECT count(*)"
				    + "FROM estReserve, Reservation"
				    + "Where Reservation.date = '"+date+"' "
				    + "AND Reservation.service = "+service+"' "
				    + "Reservation.numeroreservation = estReserve.numeroreservation"
				    + "estReserve.numerotable ="+ numeroTable);
        System.out.println(requete);
	try {
	    setStmt(getCon().createStatement());
	    ResultSet rset = getStmt().executeQuery(requete);
	    rset.close();
	    getStmt().close();
	    if (!rset.isBeforeFirst()) {
		return false;
	    }
            rset.next();
	    int res = rset.getInt(1);
	    if (res == 1) {
		return true; //pas sur que ca marche
	    } else {
		System.out.println("Plusieurs reservations correspondent, ce n'est pas logique");
		return false;
	    }
	}
	catch (SQLException e) {
	    System.err.println("Erreur pour faire la requête.");
	    e.printStackTrace(System.err);
	    return false;
	}
    }
    
}
