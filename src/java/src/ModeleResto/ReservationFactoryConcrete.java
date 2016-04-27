package ModeleResto;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import ControleurResto.*;
//import oracle.jdbc.driver.OracleDriver;

public class ReservationFactoryConcrete extends ReservationFactory{

	final private static ReservationFactoryConcrete instanceUnique = new ReservationFactoryConcrete();

	final private Article article_BD = new Article();
	final private Client client_BD = new Client();
	final private Table table_BD = new Table();
	final private Service service_BD = new Service();

	private HashMap<Integer,ReservationConcrete> reservations;

	private final String URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
	private String USR;
	private String PSWD;

	private ReservationFactoryConcrete() {
		System.out.print("Entrez votre nom d'utilisateur pour vous connecter à votre BD : ");
		Scanner sc = new Scanner(System.in);
		USR = sc.next();
		PSWD = USR;

		try {
			System.out.println("Chargement du driver Oracle ... ");
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Chargement réussi.");

			System.out.print("Connection à la base de données ... ");
			setCon(DriverManager.getConnection(URL, USR, PSWD));
			System.out.println("Connection réussie.");

			getCon().setAutoCommit(false);

			article_BD.setCon(getCon());
			client_BD.setCon(getCon());
			table_BD.setCon(getCon());
			service_BD.setCon(getCon());
		}
		catch (SQLException e) {
			System.err.println("ECHEC de la connection à la BD.");
			e.printStackTrace(System.err);
		}

		reservations = new HashMap<Integer, ReservationConcrete>();
		// Ajoute dans reservations toutes les réservations futures.
	}

	public ResultSet initRes(HashMap<Integer, ReservationConcrete> reservations) {
		String requete = new String("SELECT numeroReservation, dateService FROM Reservation");
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			return rset;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête initRes.");
			e.printStackTrace(System.err);
			return null;
		}
	}

	public int getNombrePersonnes(int numResa) {
		String requete = new String("SELECT nbPersonnes FROM Reservation ");
		int ret = 0;
		requete += "WHERE numeroReservation = " + numResa;
		System.out.println(requete);
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
			System.err.println("Erreur pour faire la requête initRes.");
			e.printStackTrace(System.err);
			return -1;
		}
	}

	/**
	 * Creer un reservation à partie du nom du client de la date , service et du nbr de personne
	 */
	public int creerReservation(int numClient, String date, String service, int nbPersonnes) {
		String requete = new String("INSERT INTO Reservation VALUES (");
		int lastRes = getMaxNombreReservations();
		requete += (lastRes + 1) +","+ nbPersonnes +","+numClient+",'"+service+"','"+date+ "')";
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			getStmt().executeUpdate(requete);
			getStmt().close();
			lastRes++;
            ReservationConcrete reserv = new ReservationConcrete(lastRes);
            if(Controleur.get().getInterface() != null){
              reserv.addObserver(Controleur.get().getInterface().getInterfaceReservation());
              reserv.addObserver(Controleur.get().getInterface().getInterfaceSuiviCommande());
            }
			reservations.put(lastRes, reserv);
            reserv.changed();
            reserv.notifyObservers(null);
			return lastRes;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête de création de resa");
			e.printStackTrace(System.err);
			return -1;
		}
	}
	/**
	 * Supprime reservation
	 * @param numeroTable numero de table
	 * @param date date
	 * @param service service (SOIR ou MIDI)
	 * @return -1 si erreur, 0 sinon
	 */
	public int supprimerReservation(int numeroTable, int numeroReservation) {
		if (numeroTable <= 0) {
			return -1;
		}
		String requete1 = new String("DELETE ");
        requete1 += "FROM Reservation ";
        requete1 += "WHERE numeroReservation = " + numeroReservation;

		String requete2 = new String("DELETE ");
        requete2 += "FROM estReservee ";
        requete2 += "WHERE numeroReservation = " + numeroReservation + " ";
        requete2 += "AND numeroTable = " + numeroTable;

		String requete3 = new String("DELETE ");
        requete3 += "FROM menuCommandes ";
        requete3 += "WHERE numeroReservation = " + numeroReservation + " ";

		String requete4 = new String("DELETE ");
        requete4 += "FROM sontCommandes ";
        requete4 += "WHERE numeroReservation = " + numeroReservation + " ";

		System.out.println(requete1);
		System.out.println(requete2);
		System.out.println(requete3);
		System.out.println(requete4);
		try {
			setStmt(getCon().createStatement());
			getStmt().executeUpdate(requete4);
			getStmt().executeUpdate(requete3);
			getStmt().executeUpdate(requete2);
			getStmt().executeUpdate(requete1);
			getStmt().close();
            reservations.get(numeroReservation).changed();
            reservations.get(numeroReservation).notifyObservers(null);
			return 0;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête supprimerReservation(table).");
			e.printStackTrace(System.err);
			return -1;
		}
	}
	/**
	 * Retourne le nom associé au numeroReservation
	 * @param numRes numero de reservation
	 * @return nom du client ayant reserve
	 */
	public String getNomRes(int numRes) {
		String res = null;
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
			if (rset.next()) {
				res = new String(rset.getString(1));
			}
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

	public int getMaxNombreReservations() {
		String requete = "SELECT MAX(numeroReservation) FROM Reservation";
		System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			if (rset.next()) {
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

	public HashMap<Integer,ReservationConcrete> getReservations(){
		return reservations;
	}

	public void editerFacture(int numResa){
		ReservationConcrete res = reservations.get(numResa);
		res.setFacture( new Facture(numResa));
	}

	public static ReservationFactoryConcrete get() {
		return instanceUnique;
	}

	public void close() {
		try {
			getCon().close();
		}
		catch (SQLException e) {
			System.err.println("ECHEC de la fermeture de la connection.");
			e.printStackTrace(System.err);
		}
	}

	public void validate() {
		try {
			getCon().commit();
		}
		catch (SQLException e) {
			System.err.println("ECHEC de la validation de la transaction.");
			e.printStackTrace(System.err);
		}
	}

	public void cancel() {
		try {
			getCon().rollback();
		}
		catch (SQLException e) {
			System.err.println("ECHEC de la validation de la transaction.");
			e.printStackTrace(System.err);
		}
	}

	public Article getArticleBD() {
		return this.article_BD;
	}

	public Table getTableBD() {
		return this.table_BD;
	}

	public Client getClientBD() {
		return this.client_BD;
	}

	public Service getServiceBD() {
		return this.service_BD;
	}
}

/*
 * ATTENTION :
 * Il faudra penser à fermer la connection à la base de donnée en sortie de l'application !
 */
