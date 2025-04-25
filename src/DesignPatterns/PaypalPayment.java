package DesignPatterns;
import MainClasses.Client;
import MainClasses.Order;

public class PaypalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PaypalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(Order order, Client client) {
        System.out.println("Processing PayPal payment for " + client.getName());
        System.out.println("Email: " + email);
        System.out.println("Paid $" + order.calculateTotalPrice() + " via PayPal.");
    }

    @Override
    public String getMethod() {
        return "PayPal";
    }
}
