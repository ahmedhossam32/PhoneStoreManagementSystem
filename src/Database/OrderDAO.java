package Database;
import MainClasses.*;
import DesignPatterns.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private final ClientDAO clientDAO = new ClientDAO();
    private final PhoneDAO phoneDAO = new PhoneDAO();

   
    public void moveOrderToNextStatus(int orderID) {
    Order order = getOrderById(orderID);

    if (order == null) {
        System.out.println("❌ Order not found.");
        return;
    }

  
    order.nextStatus();
    String newStatus = order.getorderStatus(); // Get the status name as string

    // Update in DB
    String updateQuery = "UPDATE orders SET order_status = ? WHERE order_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

        stmt.setString(1, newStatus);
        stmt.setInt(2, orderID);

        int rows = stmt.executeUpdate();

        if (rows > 0) {
            System.out.println("✅ Order #" + orderID + " moved to next status: " + newStatus);
        } else {
            System.out.println("❌ Failed to update order status in DB.");
        }

    } catch (SQLException e) {
        System.out.println("❌ Database error: " + e.getMessage());
    }
}

  public Order addOrder(Order order) {
    String query = "INSERT INTO orders (client_id, phone_id, quantity, order_date, order_status, payment_method, payment_status, amount) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setInt(1, order.getClient().getClientID());
        stmt.setInt(2, order.getPhone().getId());
        stmt.setInt(3, order.getQuantity());
        stmt.setTimestamp(4, Timestamp.valueOf(order.getOrderDate()));
        stmt.setString(5, order.getorderStatus());

        if (order.getPayment() != null && order.getPayment().getPaymentStrategy() != null) {
            stmt.setString(6, order.getPayment().getPaymentStrategy().getMethod());
        } else {
            stmt.setString(6, "Unknown");
        }

        stmt.setString(7, order.getPayment() != null ? order.getPayment().getPaymentStatus() : "Pending");
        stmt.setDouble(8, order.calculateTotalPrice());

        stmt.executeUpdate();

        // Retrieve and assign the generated ID
        ResultSet keys = stmt.getGeneratedKeys();
        if (keys.next()) {
            int generatedId = keys.getInt(1);
            order.setOrderID(generatedId);
        }

        System.out.println("✅ Order added successfully. Order ID: " + order.getOrderID());

    } catch (SQLException e) {
        System.out.println("❌ Failed to add order: " + e.getMessage());
    }

    return order;
}



public Order getOrderById(int orderID) {
    String query = "SELECT * FROM orders WHERE order_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, orderID);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) { // ✅ You MUST call rs.next() before reading values
            int id = rs.getInt("order_id"); // now safe to access

            Client client = clientDAO.getClientById(rs.getInt("client_id"));
            Phone phone = phoneDAO.getPhoneById(rs.getInt("phone_id"));
            LocalDateTime date = rs.getTimestamp("order_date").toLocalDateTime();
            String status = rs.getString("order_status");
            String method = rs.getString("payment_method");
            String paymentStatus = rs.getString("payment_status");
            double amount = rs.getDouble("amount");
            int quantity = rs.getInt("quantity");

            OrderStatus orderStatus = OrderStatusFactory.fromString(status);
            PaymentStrategy strategy = PaymentFactory.createPayment(method);

            Order order = new Order(id, phone, client, quantity, date);
            order.setOrderstatus(orderStatus);

            Payment payment = new Payment(order, client, amount, strategy, date);
            payment.setPaymentStatus(paymentStatus);
            order.setPayment(payment);

            return order;
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to fetch order by ID: " + e.getMessage());
    }

    return null;
}
 public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Client client = clientDAO.getClientById(rs.getInt("client_id"));
                Phone phone = phoneDAO.getPhoneById(rs.getInt("phone_id"));
                LocalDateTime date = rs.getTimestamp("order_date").toLocalDateTime();
                String status = rs.getString("order_status");
                String method = rs.getString("payment_method");
                String paymentStatus = rs.getString("payment_status");
                double amount = rs.getDouble("amount");
                int quantity = rs.getInt("quantity");

              
                Order order = new Order(phone, client, quantity, date);
                order.setOrderstatus(OrderStatusFactory.fromString(status));
                Payment payment = new Payment(order, client, amount, PaymentFactory.createPayment(method), date);
                payment.setPaymentStatus(paymentStatus);
                order.setPayment(payment);

                orders.add(order);
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch orders: " + e.getMessage());
        }

        return orders;
    }  
 public List<Order> getOrdersByClientId(int clientId) {
    List<Order> clientOrders = new ArrayList<>();
    String query = "SELECT * FROM orders WHERE client_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, clientId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int orderId = rs.getInt("order_id"); // ✅ Get correct order ID
            Client client = clientDAO.getClientById(clientId);
            Phone phone = phoneDAO.getPhoneById(rs.getInt("phone_id"));
            LocalDateTime date = rs.getTimestamp("order_date").toLocalDateTime();
            String status = rs.getString("order_status");
            String method = rs.getString("payment_method");
            String paymentStatus = rs.getString("payment_status");
            double amount = rs.getDouble("amount");
            int quantity = rs.getInt("quantity");

            Order order = new Order(orderId, phone, client, quantity, date); // ✅ Use the right constructor
            order.setOrderstatus(OrderStatusFactory.fromString(status));

            Payment payment = new Payment(order, client, amount, PaymentFactory.createPayment(method), date);
            payment.setPaymentStatus(paymentStatus);
            order.setPayment(payment);

            clientOrders.add(order);
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to fetch orders: " + e.getMessage());
    }

    return clientOrders;
}

    public void cancelOrder(int orderID) {
    String query = "UPDATE orders SET order_status = ? WHERE order_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, "cancelled"); // Mark as cancelled
        stmt.setInt(2, orderID);

        int rows = stmt.executeUpdate();

        if (rows > 0) {
            System.out.println("✅ Order #" + orderID + " cancelled successfully.");
        } else {
            System.out.println("❌ Order #" + orderID + " not found.");
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to cancel order: " + e.getMessage());
    }
}
    
   public boolean updateOrder(Order order) {
    String query = "UPDATE orders SET phone_id = ?, quantity = ?, payment_method = ?, payment_status = ?, amount = ? WHERE order_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, order.getPhone().getId());
        stmt.setInt(2, order.getQuantity());
        stmt.setString(3, order.getPayment().getPaymentStrategy().getMethod());
        stmt.setString(4, order.getPayment().getPaymentStatus());
        stmt.setDouble(5, order.calculateTotalPrice());
        stmt.setInt(6, order.getOrderID());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        System.out.println("❌ Failed to update order: " + e.getMessage());
        return false;
    }
}
   public void markOrderAsNotified(int orderId) {
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement("UPDATE orders SET status_notified = TRUE WHERE order_id = ?")) {
        stmt.setInt(1, orderId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}





}
