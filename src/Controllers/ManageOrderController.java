package Controllers;

import Database.*;
import MainClasses.*;

public class ManageOrderController {
    private PhoneDAO phoneDAO = new PhoneDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private PaymentService paymentService = new PaymentService();

    public Order getOrderById(int orderId) {
        return orderDAO.getOrderById(orderId);
    }

    public Phone getPhoneByModelName(String modelName) {
        return phoneDAO.getPhoneByModelName(modelName);
    }

    public boolean updateOrder(Order order) {
        return orderDAO.updateOrder(order);
    }

    public boolean cancelOrder(int orderId) {
        orderDAO.cancelOrder(orderId);
        return true;  
    }
}
