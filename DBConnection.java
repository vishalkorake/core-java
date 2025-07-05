import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/mess_system";
    private static final String USER = "root"; 
    private static final String PASSWORD = "root"; 

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connection successful!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }
        return conn;
    }

    
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("🎉 Database is connected and ready to use.");
            try {
                conn.close();
                System.out.println("🔒 Connection closed.");
            } catch (SQLException e) {
                System.out.println("⚠️ Failed to close connection.");
                e.printStackTrace();
            }
        } else {
            System.out.println("🚫 Could not establish database connection.");
        }
    }
}
