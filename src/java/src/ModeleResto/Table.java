package ModeleResto;
//TO DO
import ControleurResto.*;
import java.util.*;
import java.sql.*;
public class Table extends Observable {

    private Connection conn;
    private Statement stmt;

    public Table(){
    }

    public void setCon(Connection conn) {
        this.conn = conn;
    }
	public Statement getStmt(){
		return this.stmt;
	}

	/**
	 * Donne les tables libre d'une localisation pour une date et un service
     * ou toutes les tables libres si la localisation n'est pas précisée
	 */
	public ResultSet getTableLibre(String loc, String date, String service) {
		int t=0;
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
			+"AND r.dateService!='"+date+"' "
			+"AND r.typeService!='"+service+"' "
			;
        System.out.println(requete);
		try {
			this.stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(requete);
			return rset;
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
			this.stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(requete);
			if (!rset.isBeforeFirst()) {
                rset.close();
                this.stmt.close();
				return -1;
			}
			else {
				int i = 0;
                rset.next();
                i = rset.getInt(1);
                rset.close();
                this.stmt.close();
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
	public ResultSet getTableVoisine(int tab){
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
			this.stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(requete);
			return rset;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête getTableVoisin.");
			e.printStackTrace(System.err);
			return null;
		}
	}

	/**
	 * Donne les numeros de table d'une resa
	 */
	public ResultSet getNumeroTable(int numResa){
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
			this.stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(requete);
			return rset;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête getNumeroTable.");
			e.printStackTrace(System.err);
			return null;
		}
	}

	/**
	 * Donne le numéro de resa pour une table donnée
	 */
	public ResultSet getNumeroReservation(int numTable){
		if (numTable <= 0) {
			return null;
		}
		String requete = new String("SELECT "
				+"er.numeroreservation "
				+"FROM estreservee er "
				+"WHERE er.numerotable ="+numTable
			);
        System.out.println(requete);
		try {
			this.stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(requete);
			return rset;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête getNumeroReservation(table).");
			e.printStackTrace(System.err);
			return null;
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
		try {
			this.stmt = conn.createStatement();
			stmt.executeUpdate(requete);
			stmt.close();
			return 0;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête.");
			e.printStackTrace(System.err);
			return -1;
		}
	}

}
