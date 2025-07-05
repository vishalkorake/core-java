import java.sql.*;

public class OwnerDAO {
    public boolean registerOwner(Owner owner) {
        String sql = "INSERT INTO owner (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, owner.getName());
            stmt.setString(2, owner.getEmail());
            stmt.setString(3, owner.getPassword());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    owner.setId(rs.getInt(1));
                    System.out.println("🆔 Registered Owner ID: " + owner.getId());
                }
                return true;
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("⚠️ Email already registered.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getOwnerId(String email, String password) {
        String sql = "SELECT id FROM owner WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
