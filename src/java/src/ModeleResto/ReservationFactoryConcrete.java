package ModeleResto;

import java.sql.*;
import java.util.*;
//import oracle.jdbc.driver.OracleDriver;

public class ReservationFactoryConcrete extends ReservationFactory{

	private HashMap<Integer,ReservationConcrete> reservations;
    final private static ReservationFactoryConcrete instanceUnique = new ReservationFactoryConcrete();

    final private Article article_BD = new Article();
    final private Client client_BD = new Client();
    final private Table table_BD = new Table();
    final private Service Service_BD = new Service();

    static final String URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
    static final String USR = "devalonh";
    static final String PSWD = "devalonh";

    private Connection conn;

    private ReservationFactoryConcrete() {
        try {
            System.out.println("Chargement du driver Oracle ...");
            //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Chargement réussi.");

            System.out.print("Connection à la base de données ...");
            conn = DriverManager.getConnection(URL, USR, PSWD);
            System.out.println("Connection réussie.");

            conn.setAutoCommit(false);

            article_BD.setCon(conn);
            client_BD.setCon(conn);
            table_BD.setCon(conn);
        }
        catch (SQLException e) {
            System.err.println("ECHEC de la connection à la BD.");
            e.printStackTrace(System.err);
        }
		reservations = new HashMap<Integer, ReservationConcrete>();
    }

	public HashMap<Integer,ReservationConcrete> getReservations(){
		return this.reservations;
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
}

/*
 * ATTENTION :
 * Il faudra penser à fermer la connection à la base de donnée en sortie de l'application !
 * On peut prévoir une méthode close() qui la ferme, à appeler à la fin.
 */
