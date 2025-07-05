import java.sql.*;

public class MessDAO {
    public boolean addMess(Mess mess) {
        String sql = "INSERT INTO mess (owner_id, name, address) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, mess.getOwnerId());
            stmt.setString(2, mess.getName());
            stmt.setString(3, mess.getAddress());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    mess.setMessId(rs.getInt(1));
                    System.out.println("🆔 Mess created with ID: " + mess.getMessId());
                }
                return true;
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("⚠️ A mess with the same name and address already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
