package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm93584", "230690");
        } catch (SQLException err){
            err.printStackTrace();
        }

        return conn;
    }
}
