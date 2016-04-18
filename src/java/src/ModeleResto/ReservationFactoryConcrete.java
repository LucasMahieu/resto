package ModeleResto;
import java.sql.*;

public class ReservationFactoryConcrete extends ReservationFactory{

    final private static ReservationFactoryConcrete instanceUnique = new ReservationFactoryConcrete();

    final public static Article article_BD = new Article();
    final public static Client client_BD = new Client();
    final public static Table table_BD = new Table();

    static final String URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
    static final String USR = "devalonh";
    static final String PSWD = "devalonh";

    private Connection conn;

    private ReservationFactoryConcrete() {
        try {
            System.out.println("Chargement du driver Oracle ...");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Chargement réussi.");

            System.out.print("Connection à la base de données ...");
            conn = DriverManager.getConnection(URL, USR, PASSWD);
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
    }

    public static ReservationFactoryConcrete get() {
        return instanceUnique;
    }

    public static void close() {
        conn.close();
    }

    public static Connection getCon() {
        return this.conn;
    }

    public static void validate() {
        conn.commit();
    }
}

/*
 * ATTENTION :
 * Il faudra penser à fermer la connection à la base de donnée en sortie de l'application !
 * On peut prévoir une méthode close() qui la ferme, à appeler à la fin.
 */
