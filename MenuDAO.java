import java.sql.*;

public class MenuDAO {
    public boolean updateTodayMenu(int messId, String breakfast, String lunch, String dinner) {
        String sql = "INSERT INTO menu (mess_id, date, breakfast, lunch, dinner) " +
                     "VALUES (?, CURDATE(), ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE breakfast = ?, lunch = ?, dinner = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, messId);
            stmt.setString(2, breakfast);
            stmt.setString(3, lunch);
            stmt.setString(4, dinner);
            stmt.setString(5, breakfast);
            stmt.setString(6, lunch);
            stmt.setString(7, dinner);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Failed to update menu.");
            e.printStackTrace();
            return false;
        }
    }
}
