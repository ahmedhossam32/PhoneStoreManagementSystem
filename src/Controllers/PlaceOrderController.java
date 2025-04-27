package Controllers;

import Database.*;
import MainClasses.*;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderController {
    private PhoneDAO phoneDAO = new PhoneDAO();
    private OrderManager orderManager = new OrderManager();

    public List<String> getAllPhoneModels() {
        List<String> models = new ArrayList<>();
        for (Phone phone : phoneDAO.getAllPhones()) {
            models.add(phone.getModelName());
        }
        return models;
    }

    public Phone getPhoneByModelName(String modelName) {
        return phoneDAO.getPhoneByModelName(modelName);
    }

    public double calculateTotalPrice(Phone phone, int quantity) {
        if (phone == null || quantity <= 0) {
            return 0;
        }
        return phone.getPrice() * quantity;
    }

    public Order createOrder(Client client, Phone phone, int quantity, String paymentMethod) {
        return orderManager.createOrder(client, phone, quantity, paymentMethod);
    }
}
