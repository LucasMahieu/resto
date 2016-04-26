package ModeleResto;
import java.util.*;
import java.sql.*;

public class BDitem extends Observable {
    
    private Connection conn;
    private Statement stmt;

    public BDitem() {
    }

    public Statement getStmt() {
        return this.stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public Connection getCon() {
        return this.conn;
    }

    public void setCon(Connection conn) {
        this.conn = conn;
    }
}
