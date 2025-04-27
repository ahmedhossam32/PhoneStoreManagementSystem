package Controllers;

import Database.OrderDAO;
import MainClasses.Order;
import java.util.List;

public class OrderHistoryController {
    private OrderDAO orderDAO = new OrderDAO();

    public List<Order> getOrdersByClientId(int clientId) {
        return orderDAO.getOrdersByClientId(clientId);
    }
}
