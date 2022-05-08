package hcmiucvip.solutionforsavingstudentrecords.core.DB;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static String URL;
    private static String username;
    private static String password;

    private static Connection connection;

    private static Connection getConnection() {
        Connection _connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            _connection = DriverManager.getConnection(URL, username, password);
            _connection.setAutoCommit(true);
            System.out.println("Connected to database!");
        } catch (Exception e) {
            System.err.println("Failed to connect to database!");
            e.printStackTrace();
        }
        return _connection;
    }

    public static Connection getDBConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void init(String dbURL, String username, String password) {
        DatabaseConnectionManager.URL = dbURL;
        DatabaseConnectionManager.username = username;
        DatabaseConnectionManager.password = password;
        connection = getConnection();
    }
}
