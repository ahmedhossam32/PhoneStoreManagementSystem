package Database;

import MainClasses.*;
import DesignPatterns.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private final ClientDAO clientDAO = new ClientDAO();
    private final OrderDAO orderDAO = new OrderDAO();

    public void addPayment(Payment payment) {
        String query = "INSERT INTO payments (order_id, client_id, payment_method, payment_status, amount, payment_date) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, payment.getOrder().getOrderID());
            stmt.setInt(2, payment.getClient().getClientID());
            stmt.setString(3, payment.getPaymentStrategy().getMethod());
            stmt.setString(4, payment.getPaymentStatus());
            stmt.setDouble(5, payment.getAmount());
            stmt.setTimestamp(6, Timestamp.valueOf(payment.getPaymentDate()));

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                payment.setPaymentID( keys.getInt(1)); // Optional formatting
                System.out.println("✅ Payment saved with ID: " + payment.getPaymentID());
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to save payment: " + e.getMessage());
        }
    }

    public Payment getPaymentByOrderId(int orderId) {
        String query = "SELECT * FROM payments WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                Client client = clientDAO.getClientById(rs.getInt("client_id"));
                Order order = orderDAO.getOrderById(orderId);
                String method = rs.getString("payment_method");
                String status = rs.getString("payment_status");
                double amount = rs.getDouble("amount");
                LocalDateTime date = rs.getTimestamp("payment_date").toLocalDateTime();

                PaymentStrategy strategy = PaymentFactory.createPayment(method);
                Payment payment = new Payment(order, client, amount, strategy, date);
                payment.setPaymentStatus(status);
                payment.setPaymentID(id);
                return payment;
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch payment: " + e.getMessage());
        }

        return null;
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                Client client = clientDAO.getClientById(rs.getInt("client_id"));
                Order order = orderDAO.getOrderById(rs.getInt("order_id"));
                String method = rs.getString("payment_method");
                String status = rs.getString("payment_status");
                double amount = rs.getDouble("amount");
                LocalDateTime date = rs.getTimestamp("payment_date").toLocalDateTime();

                PaymentStrategy strategy = PaymentFactory.createPayment(method);
                Payment payment = new Payment(order, client, amount, strategy, date);
                payment.setPaymentStatus(status);
                payment.setPaymentID( id);
                payments.add(payment);
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch payments: " + e.getMessage());
        }

        return payments;
    }

    public void updatePaymentStatus(int paymentId, String newStatus) {
        String query = "UPDATE payments SET payment_status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, paymentId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Payment ID P" + paymentId + " status updated to " + newStatus);
            } else {
                System.out.println("⚠️ No payment found with ID: " + paymentId);
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to update payment status: " + e.getMessage());
        }
    }
    public void updatePaymentMethod(int paymentId, String newMethod) {
    String query = "UPDATE payments SET payment_method = ? WHERE payment_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, newMethod);
        stmt.setInt(2, paymentId);

        stmt.executeUpdate();
        System.out.println("✅ Payment method updated.");

    } catch (SQLException e) {
        System.out.println("❌ Failed to update payment method: " + e.getMessage());
    }
}

public void updateAmount(int paymentId, double newAmount) {
    String query = "UPDATE payments SET amount = ? WHERE payment_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setDouble(1, newAmount);
        stmt.setInt(2, paymentId);

        stmt.executeUpdate();
        System.out.println("✅ Payment amount updated.");

    } catch (SQLException e) {
        System.out.println("❌ Failed to update payment amount: " + e.getMessage());
    }
}

} 
