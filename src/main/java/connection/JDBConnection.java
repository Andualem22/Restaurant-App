package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant_db";
    private static final String USER = "root";
    private static final String PASSWORD = "14@ANdy#";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL,USER,PASSWORD);
    }
}


