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

    // On a déjà 4 réservations dans la BD
    private int lastRes = 4;

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
        initRes(reservations);
        lastRes = getNombreReservations();
        client_BD.setLastClient(client_BD.getNombreClient());
    }

    /*
     * Ajoute dans l'état initial, au lancement de l'application
     * les réservations d'aujourd'hui et futures.
     */
    public int initRes(HashMap<Integer, ReservationConcrete> reservations) {
		String requete = new String("SELECT numeroReservation, dateService FROM Reservation");
		try {
			setStmt(getCon().createStatement());
			ResultSet rset = getStmt().executeQuery(requete);
			while (rset.next()) {
				int numRes = rset.getInt(1);
                String date = rset.getString(2);
                if (turfu(date)) {
                    reservations.put(numRes, new ReservationConcrete(numRes));
                }
				rset.close();
				getStmt().close();
			}
            return 0;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête initRes.");
			e.printStackTrace(System.err);
			return -1;
		}
    }

    public boolean turfu(String date) {
        String dateNow = Controleur.get().getDateNow();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
        Date nowDate;
        Date thisDate;
        try {
            nowDate = df.parse(dateNow);
            thisDate = df.parse(date);
            if (nowDate.compareTo(thisDate) <= 0) {
                return true;
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int creerReservation(int numClient, String date, String service, int nbPersonnes) {
		String requete = new String("INSERT INTO Reservation VALUES (");
		requete += (lastRes + 1) +","+ nbPersonnes +","+numClient+",'"+service+"','"+date+ "')";
        System.out.println(requete);
		try {
			setStmt(getCon().createStatement());
			getStmt().executeUpdate(requete);
			getStmt().close();
            lastRes++;
			reservations.put(lastRes, new ReservationConcrete(lastRes));
			return lastRes;
		}
		catch (SQLException e) {
			System.err.println("Erreur pour faire la requête de création de resa");
			e.printStackTrace(System.err);
			return -1;
		}
    }

	public int getNombreReservations() {
		String requete = "SELECT COUNT(*) FROM Reservation";
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

	public HashMap<Integer,ReservationConcrete> getReservations(){
		return reservations;
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
