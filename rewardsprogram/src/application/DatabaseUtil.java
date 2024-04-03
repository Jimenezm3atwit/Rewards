package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/cashreturnsdb";
    private static final String DATABASE_USER = "root"; // Replace with your database username
    private static final String DATABASE_PASSWORD = "Aralyn2024!!"; // Replace with your database password

    static {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}
