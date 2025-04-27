package Controllers;
import Database.OrderDAO;
import DesignPatterns.PaymentStrategy;
import MainClasses.*;
import MainClasses.Order;

public class PayOrderController {
    private OrderDAO orderDAO = new OrderDAO();
    private PaymentService paymentService = new PaymentService();

    public Order getOrderById(int orderId) {
        return orderDAO.getOrderById(orderId);
    }

    public void payOrder(Client client, Order order, PaymentStrategy strategy) {
        paymentService.pay(client, order, strategy);
    }
}
