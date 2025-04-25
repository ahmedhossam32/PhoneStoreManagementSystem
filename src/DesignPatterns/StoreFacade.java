//package DesignPatterns;
//import java.time.LocalDateTime;
//import MainClasses.*;
//
//public class StoreFacade
//{
//    private final PhoneStore store = PhoneStore.getInstance();
//
//    public void placeOrderAndPay(Client client, Phone phone, int quantity, PaymentStrategy strategy) {
//       
//        if (phone.getStock() < quantity) {
//            System.out.println("❌ Not enough stock for " + phone.getModelName());
//            return;
//        }
//
//       
//        Order order = store.getOrderManager().createOrder(client, phone, quantity);
//        client.placeOrder(order);
//        if (order == null) {
//            System.out.println("❌ Order creation failed.");
//            return;
//        }
//
//        
//        double totalAmount = order.calculateTotalPrice();
//        Payment payment = new Payment(order, client, totalAmount, strategy, LocalDateTime.now());
//        payment.processPayment();
//        payment.confirmPayment();
//        
//        if (payment.isSuccessful()) {
//            System.out.println("✅ Payment confirmed for Order ID: " + order.getOrderID());
//            
//            payment.paymentReceipt(); 
//        } else {
//            System.out.println("❌ Payment failed. Status: " + payment.getPaymentStatus());
//        }
//    }
//}
//
//
