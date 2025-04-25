package Database;

import MainClasses.Client;
import MainClasses.PhoneStore;
import java.sql.*;

public class ClientDAO {
public void addClient(Client client) {
    String query = "INSERT INTO clients (username, password, name, email) VALUES (?, ?, ?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, client.getUsername());
        stmt.setString(2, client.getPassword());
        stmt.setString(3, client.getName());
        stmt.setString(4, client.getEmail());
        stmt.executeUpdate();
        ResultSet keys = stmt.getGeneratedKeys();
        if (keys.next()) {
            client.setClientID(keys.getInt(1));
        }

        System.out.println("✅ Client added successfully with ID: " + client.getClientID());

    } catch (SQLIntegrityConstraintViolationException e) {
        System.out.println("❌ Username already exists.");
    } catch (SQLException e) {
        System.out.println("❌ Failed to add client: " + e.getMessage());
    }
}
public Client getClientById(int id) {
    String query = "SELECT * FROM clients WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Client client = new Client(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"),
                    PhoneStore.getInstance()
            );
            client.setClientID(id); // ✅ Set ID explicitly
            return client;
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to fetch client by ID: " + e.getMessage());
    }

    return null;
}
public Client getClientByUsernameAndPassword(String username, String password) {
    String query = "SELECT * FROM clients WHERE username = ? AND password = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Client client = new Client(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                PhoneStore.getInstance()
            );
            client.setClientID(rs.getInt("id"));
            return client;
        }

    } catch (SQLException e) {
        System.out.println("❌ Login failed: " + e.getMessage());
    }

    return null;
}
public Client getClientByUsername(String username) {
    String query = "SELECT * FROM clients WHERE username = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Client client = new Client(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                PhoneStore.getInstance()
            );
            client.setClientID(rs.getInt("id"));
            return client;
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to fetch client by username: " + e.getMessage());
    }

    return null;
}


}
