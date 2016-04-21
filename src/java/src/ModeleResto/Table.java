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

    /*
     * Si localisation est NULL, on renvoit le plus petit groupe de table possible.
     * Sinon, on renvoit le plus petit groupe de table possible dans cette localisation.
     * Si nombrePlace = 0, on renvoit les tables libres de cette localisation.
     * Si les deux sont nuls, on renvoit toutes les tables libres.
     */
    public ResultSet consultationTableLibre (String localisation, int nombrePlace) {

        String requeteNombreMaxTable = new String("SELECT count(*) from tables group by tables.localisation");
        //On fait la requete et on met le resultat dans un int nbTable
        int nombreMaxTable = 56;
        int nbTableActuel = 0;
        int nombrePlaceDisponible = 0;

        while (!(nbTableActuel <= nombreMaxTable)) {
            nbTableActuel += 1;
            String requete = new String("SELECT ");

            //on ajoute nbTableActuel tables à la requete 
            for(int i = 0; i < nbTableActuel; i++) { 
                requete += ("T" + i + ".numerotable,");
            }

            //on calcule la somme des tables considérées
            requete += "SUM(";
            for(int i = 0; i < nbTableActuel; i++) { 
                requete += ("T" + i + ".nombreplaceisolee + ");
            }
            //on enlève le "+ " de fin
            requete = requete.substring(0, requete.length() - 2);
            requete += ")as somme";
            requete += "CASE ";
            requete += "when somme >= 'nombre' THEN 'OK' ELSE '0' ";
            requete += "END";
            requete += "FROM estreservee E, ";
            for(int i = 0; i < nbTableActuel; i++) { 
                requete += "tables T" + i + ", ";
            }
            //on enlève le ", " de fin
            requete = requete.substring(0, requete.length() - 2);
            requete += "WHERE T0.numerotable not in (SELECT numeroreservation From estreservee)";
            //on rajoute la meme condition, en partant de 1 cette fois
            for(int i = 1; i < nbTableActuel; i++) { 
                requete += "and T" + i + ".numerotable not in SELECT numeroreservation From estreservee)";
            }
            //Il faut que les tables soient voisines s'il y en a plusieurs (on va jusqu'à -1 du coup
            for(int i = 0; i < nbTableActuel - 1; i++) { 
                requete += "and exists (SELECT SS.numerotable" + i + ", SS.numerotable" + (i+1) + "From sontvoisine SS where (SS.numerotable" + i + " = T" + i + ".numerotable and SS.numerotable" + (i+1) + " = T" + (i+1) + ".numerotable) or (SS.numerotable" + i + " = T" + (i+1) + ".numerotable and SS.numerotable" + (i+1) + " = T" + (i+1) + ".numerotable)";
            }


            //on fait la requete et on met la réponse dans nombrePlaceDisponible
            //??? sait pas comment faire


        }  
        return null;
    }
	/**
	 * Donne les tables libre d'une localisation pour une date et service
	 * MARCHE DANS LA BD 
	 */
	public ResultSet getTableLibre(String loc, String date, String service) {
		if (loc == "") {
			return null;
		}
		ArrayList<Integer> ret = new ArrayList<Integer>(); 
		int t=0;
		String requete = new String(
				"SELECT t.numeroTable "
				+"FROM tables t "
				+"WHERE t.localisation='"+loc+"' "
				+"MINUS "
				+"SELECT er.numerotable "
				+"FROM estreservee er, reservation r "
				+"WHERE er.numeroreservation = r.numeroreservation "
				+"AND r.dateService='"+date+"' "
				+"AND r.typeService='"+service+"' "
				);
		try {
			this.stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(requete);
			return rset;
		/*	if (!rset.isBeforeFirst()) {
				return ret;
			}
			else {
				while(rset.next()){
					ret.add(rset.getInt(1));
				}
				return ret;
			}
			*/
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête tableLibre.");
			e.printStackTrace(System.err);
			return null;
		}
	}
	/**
	 * Donne le nombre de place d'une table donnée.
	 * @param tab numero de la table 
	 * @param config 0 pour table isolée, 1 pour accolée à 1 table, 2 pour accolée2 à 2 table voisines
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
			+"WHERE t.numeroTable='"+tab+"' ";
		try {
			this.stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(requete);
			if (!rset.isBeforeFirst()) {
				return -1;
			}
			else {
				int i = 0;
				while(rset.next()){
					i = rset.getInt(1);
					rset.close();
					return i;
				}
				return -1;
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
				+"FROM tables t, sontvoisines sv "
				+"WHERE t.numerotable='"+tab+"' " 
				+"AND t.numerotable=sv.numerotable1 "
			);
		ArrayList<Integer> ret = new ArrayList<Integer>(); 
		try {
			this.stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(requete);
			return rset;
		/*	if (!rset.isBeforeFirst()) {
				return ret;
			}
			else {
				while(rset.next()){
					ret.add(rset.getInt(1));
				}
				return ret;
			}
			*/
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
				+"WHERE er.numeroreservation='"+numResa+"' " 
			);
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
				+"WHERE er.numerotable ='"+numTable+"' " 
			);
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
