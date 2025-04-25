package MainClasses;

import Database.OrderDAO;
import java.time.LocalDateTime;
import java.util.List;

public class OrderManager {
    private final OrderDAO orderDAO = new OrderDAO();
    LocalDateTime paymentDate;

   
 public Order createOrder(Client client, Phone phone, int quantity, String method) {
    if (!phone.isAvailable() || phone.getStock() < quantity) {
        System.out.println("Insufficient stock to place order.");
        return null;
    }

    phone.reduceStock(quantity);

    Order order = new Order(phone, client, quantity, LocalDateTime.now());
    Payment payment = new PaymentService().createInitialPayment(order, client, method);
    payment.setPaymentStatus("Pending");
    order.setPayment(payment);

    order = orderDAO.addOrder(order);
    printOrderReport(client, order);

    return order;
}



    
    public void printOrderReport(Client client, Order order) {
        System.out.println("************************************************");
        System.out.println("Thanks for ordering, " + client.getName() +
                ". Your order ID is " + order.getOrderID() + ".");
        System.out.println("Order ID: " + order.getOrderID());
        System.out.println("Phone: " + order.getPhone().getModelName());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.println("Total: $" + order.calculateTotalPrice());
        System.out.println("Date: " + order.getOrderDate());
        System.out.println("-------------------------------------");
    }

   
    public void printOrdersOfClient(Client client) {
        List<Order> orders = orderDAO.getOrdersByClientId(client.getClientID());
        System.out.println("Orders of Client: " + client.getName());
        System.out.println("_____________________________________________");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println("Order number " + (i + 1));
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Phone: " + order.getPhone().getModelName());
            System.out.println("Quantity: " + order.getQuantity());
            System.out.println("Total: $" + order.calculateTotalPrice());
            System.out.println("Date: " + order.getOrderDate());
            System.out.println("-------------------------------------");
        }
    }

   
    public void moveOrderToNextStatus(int orderID) {
    orderDAO.moveOrderToNextStatus(orderID);
}
    public Order getOrderById(int orderID) {
    return orderDAO.getOrderById(orderID);
}

    
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }
    
    public List<Order> getOrdersByClient(Client client) {
    return orderDAO.getOrdersByClientId(client.getClientID());
}


   public void cancelOrder(int orderID) {
    orderDAO.cancelOrder(orderID);
}


    
}
