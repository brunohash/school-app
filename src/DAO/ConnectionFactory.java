package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("HOST", "USU", "PW");
        } catch (SQLException err){
            err.printStackTrace();
        }

        return conn;
    }
}
