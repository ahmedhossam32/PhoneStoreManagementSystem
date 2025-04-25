package Database;

import MainClasses.Admin;
import MainClasses.PhoneStore;

import java.sql.*;

public class AdminDAO {
    public void addAdmin(Admin admin) {
        String query = "INSERT INTO admins (username, password, name, email, admin_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            stmt.setString(3, admin.getName());
            stmt.setString(4, admin.getEmail());
            stmt.setString(5, admin.getAdminID());

            stmt.executeUpdate();
            System.out.println("✅ Admin added successfully.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("❌ Username already exists.");
        } catch (SQLException e) {
            System.out.println("❌ Failed to add admin: " + e.getMessage());
        }
    }
    public Admin getAdminByUsername(String username) {
        String query = "SELECT * FROM admins WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Admin(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("admin_id"),
                        PhoneStore.getInstance()
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch admin: " + e.getMessage());
        }

        return null;
    }
    public Admin getAdminByUsernameAndPassword(String username, String password) {
    String query = "SELECT * FROM admins WHERE username = ? AND password = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Admin(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("admin_id"),
                PhoneStore.getInstance()
            );
        }

    } catch (SQLException e) {
        System.out.println("❌ Admin login failed: " + e.getMessage());
    }

    return null;
}

}
