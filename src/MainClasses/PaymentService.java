package MainClasses;

import DesignPatterns.*;
import java.time.LocalDateTime;
import Database.*;

public class PaymentService {
    private PaymentDAO paymentd = new PaymentDAO();

    public void pay(Client client, Order order, PaymentStrategy strategy) {
        Payment payment = new Payment(order, client, order.calculateTotalPrice(), strategy, LocalDateTime.now());
        paymentd.addPayment(payment);
        payment.processPayment();
        payment.confirmPayment();
        paymentd.updatePaymentStatus(payment.getPaymentID(), payment.getPaymentStatus());
    }

    public void updatePaymentStatus(int paymentId, String newStatus) {
        paymentd.updatePaymentStatus(paymentId, newStatus);
    }

    public Payment createInitialPayment(Order order, Client client, String method) {
    PaymentStrategy strategy = PaymentFactory.createPayment(method); // e.g., "Cash" or "Visa"
    return new Payment(order, client, order.calculateTotalPrice(), strategy, LocalDateTime.now());
}

}
