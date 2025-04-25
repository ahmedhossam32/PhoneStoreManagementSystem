package MainClasses;

import java.time.LocalDateTime;
import DesignPatterns.*;

public class Payment {
    private int paymentID; 
    private Order order;
    private Client client;
    private double amount;
    private PaymentStrategy paymentStrategy;
    private LocalDateTime paymentDate;
    private String paymentStatus;

    public Payment(Order order, Client client, double amount, PaymentStrategy paymentStrategy, LocalDateTime paymentDate) {
        this.order = order;
        this.client = client;
        this.amount = amount;
        this.paymentStrategy = paymentStrategy;
        this.paymentDate = paymentDate;
        this.paymentStatus = "Pending";
    }

    public void confirmPayment() {
        this.paymentStatus = "Confirmed";
        updateStatusInDB();
        
    }

    public void failPayment(String reason) {
        this.paymentStatus = "Failed: " + reason;
        updateStatusInDB();
    }

    public boolean isSuccessful() {
        updateStatusInDB();
        return "Confirmed".equals(paymentStatus);
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public Order getOrder() {
        return order;
    }

    public Client getClient() {
        return client;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void processPayment() {
        paymentStrategy.pay(order, client);
    }

    public void paymentReceipt() {
        System.out.println("🧾 ---------------- PAYMENT RECEIPT ----------------");
        System.out.println("Payment ID: " + paymentID);
        System.out.println("Client: " + client.getName());
        System.out.println("Order ID: " + order.getOrderID());
        System.out.println("Phone: " + order.getPhone().getModelName());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.println("Total Paid: $" + amount);
        System.out.println("Payment Method: " + paymentStrategy.getMethod());
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Status: " + paymentStatus);
        System.out.println("---------------------------------------------------");
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
   
  private void updateStatusInDB() {
    if (paymentID != 0) {
       PaymentService  paymentService = new PaymentService();
       paymentService.updatePaymentStatus(paymentID, paymentStatus);
      
    } else {
        System.out.println("❌ Cannot update payment status: Payment ID is not set.");
    }
}

}
