package Database;

import MainClasses.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {

    private final ClientDAO clientDAO = new ClientDAO();

    public void addRequest(RequestPhone request) {
        String query = "INSERT INTO requests (client_id, model_name, request_date, is_notified) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, request.getClient().getClientID());
            stmt.setString(2, request.getModelRequested());
            stmt.setTimestamp(3, Timestamp.valueOf(request.getRequestDate()));
            stmt.setBoolean(4, request.isNotified());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                request.setRequestID(keys.getInt(1));
            }

            System.out.println("✅ Phone request added for client ID " + request.getClient().getClientID());

        } catch (SQLException e) {
            System.out.println("❌ Failed to add phone request: " + e.getMessage());
        }
    }

    public List<RequestPhone> getAllUnnotifiedRequests() {
    List<RequestPhone> requests = new ArrayList<>();
    String query = "SELECT * FROM requests WHERE is_notified = false";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int requestId = rs.getInt("id");
            Client client = clientDAO.getClientById(rs.getInt("client_id"));
            String model = rs.getString("model_name");
            LocalDateTime date = rs.getTimestamp("request_date").toLocalDateTime();
            boolean notified = rs.getBoolean("is_notified");

            RequestPhone request = new RequestPhone(requestId, client, model, date, notified);
            requests.add(request);
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to fetch all unnotified requests: " + e.getMessage());
    }

    return requests;
}
    
    public void markAsNotified(int requestID) {
    String query = "UPDATE requests SET is_notified = true WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, requestID);
        stmt.executeUpdate();

        System.out.println("✅ Request ID " + requestID + " marked as notified.");

    } catch (SQLException e) {
        System.out.println("❌ Failed to mark request as notified: " + e.getMessage());
    }
}


    
} 
