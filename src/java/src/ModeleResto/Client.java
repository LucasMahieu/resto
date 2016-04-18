package ModeleResto;
import java.util.*;
import java.sql.*;
public class Client extends Observable {
    private Connection conn;
	public Client(){
	}

    public void setCon(Connection conn) {
        this.conn = conn;
    }

}
