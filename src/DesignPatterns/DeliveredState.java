package DesignPatterns;

import MainClasses.*;

public class DeliveredState implements OrderStatus {

    @Override
    public void nextStatus(Order order) {
        System.out.println("Order #" + order.getOrderID() + " has already been delivered. No further state transitions allowed.");
    }

    @Override
    public String getStatus() {
        return "Delivered";
    }

    @Override
    public double cancellationFees(Order order) {
        System.out.println("Order #" + order.getOrderID() + " is already delivered. Cannot be cancelled.");
        return 0.0;
    }

    @Override
    public boolean canItBeCancelled() {
        return false; 
    }
}
