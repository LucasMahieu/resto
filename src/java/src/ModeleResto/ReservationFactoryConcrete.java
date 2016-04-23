package ModeleResto;

import java.sql.*;
import java.util.*;
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

    private Connection conn;
    private Statement stmt;

    private int lastRes = 0;

	public Statement getStmt(){
		return this.stmt;
	}

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
            conn = DriverManager.getConnection(URL, USR, PSWD);
            System.out.println("Connection réussie.");

            conn.setAutoCommit(false);

            article_BD.setCon(conn);
            client_BD.setCon(conn);
            table_BD.setCon(conn);
            service_BD.setCon(conn);
        }
        catch (SQLException e) {
            System.err.println("ECHEC de la connection à la BD.");
            e.printStackTrace(System.err);
        }
		reservations = new HashMap<Integer, ReservationConcrete>();
    }

    public int creerReservation(int numClient, String date, String service, int nbPersonnes) {
		String requete = new String("INSERT INTO Reservation VALUES (");
		requete += (lastRes + 1) +","+ nbPersonnes +","+"0"+","+numClient+",'"+service+"','"+date+ "')";
        System.out.println(requete);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(requete);
			stmt.close();
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

	public HashMap<Integer,ReservationConcrete> getReservations(){
		return reservations;
	}

    public static ReservationFactoryConcrete get() {
        return instanceUnique;
    }

    public void close() {
        try {
            conn.close();
        }
        catch (SQLException e) {
            System.err.println("ECHEC de la fermeture de la connection.");
            e.printStackTrace(System.err);
        }
    }

    public Connection getCon() {
        return this.conn;
    }

    public void validate() {
        try {
            conn.commit();
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
