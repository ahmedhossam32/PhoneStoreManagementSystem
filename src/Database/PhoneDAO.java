package Database;

import MainClasses.Phone;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDAO {

   public void addOrUpdatePhone(Phone phone) {
    String checkQuery = "SELECT * FROM phones WHERE model_name = ? AND brand = ?";
    String insertQuery = "INSERT INTO phones (model_name, brand, price, stock) VALUES (?, ?, ?, ?)";
    String updateQuery = "UPDATE phones SET stock = stock + ?, price = ? WHERE model_name = ? AND brand = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {

        checkStmt.setString(1, phone.getModelName());
        checkStmt.setString(2, phone.getBrand());

        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // ✅ Phone exists — update stock
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                updateStmt.setInt(1, phone.getStock());
                updateStmt.setDouble(2, phone.getPrice());
                updateStmt.setString(3, phone.getModelName());
                updateStmt.setString(4, phone.getBrand());

                updateStmt.executeUpdate();
                System.out.println("✅ Existing phone stock updated.");
            }
        } else {
            // ✅ New phone — insert it
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, phone.getModelName());
                insertStmt.setString(2, phone.getBrand());
                insertStmt.setDouble(3, phone.getPrice());
                insertStmt.setInt(4, phone.getStock());

                insertStmt.executeUpdate();
                System.out.println("✅ New phone added.");
            }
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to add/update phone: " + e.getMessage());
    }
}
   public Phone getPhoneByModelName(String modelName) {
    String query = "SELECT * FROM phones WHERE model_name = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, modelName);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Phone phone = new Phone(
                rs.getString("model_name"),
                rs.getString("brand"),
                rs.getDouble("price"),
                rs.getInt("stock")
            );
            phone.setId(rs.getInt("phone_id"));
            return phone;
        }
    } catch (SQLException e) {
        System.out.println("❌ Error fetching phone: " + e.getMessage());
    }
    return null;
}


    public List<Phone> getAllPhones() {
        List<Phone> phones = new ArrayList<>();
        String query = "SELECT * FROM phones";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Phone phone = new Phone(
                        rs.getString("model_name"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );

                // ✅ Set phone ID
                phone.setId(rs.getInt("phone_id"));

                phones.add(phone);
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch phones: " + e.getMessage());
        }

        return phones;
    }
    public Phone getPhoneById(int id) {
    String query = "SELECT * FROM phones WHERE phone_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Phone phone = new Phone(
                    rs.getString("model_name"),
                    rs.getString("brand"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
            );
            phone.setId(id); // ✅ Set ID explicitly
            return phone;
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to fetch phone by ID: " + e.getMessage());
    }

    return null;
}
    public void updatePhoneByModel(String modelName, double newPrice, int newStock) {
        String query = "UPDATE phones SET price = ?, stock = ? WHERE model_name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, newPrice);
            stmt.setInt(2, newStock);
            stmt.setString(3, modelName);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Phone updated successfully.");
            } else {
                System.out.println("ℹ️ No phone found with model name: " + modelName);
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to update phone: " + e.getMessage());
        }
    }
    public void deletePhoneByModel(String modelName) {
        String query = "DELETE FROM phones WHERE model_name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, modelName);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Phone deleted successfully.");
            } else {
                System.out.println("ℹ️ No phone found with model name: " + modelName);
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to delete phone: " + e.getMessage());
        }
    }
}
