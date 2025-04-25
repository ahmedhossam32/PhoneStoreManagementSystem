package DesignPatterns;

import MainClasses.Order;
import MainClasses.Payment;

public class ShippedState implements OrderStatus 
{
   @Override
     public void nextStatus(Order order) {
        order.setOrderstatus(new DeliveredState()); 
        System.out.println("Order #" + order.getOrderID() + " has been Delievered.");
    }

@Override
public String getStatus() {
    return "Shipped"; 
}

@Override
public double cancellationFees(Order order) {
   Payment payment=order.getPayment();
    if(payment.isSuccessful() && canItBeCancelled() )
    {
        return 3%order.calculateTotalPrice();
    }
    
        return 5%order.calculateTotalPrice();
}

@Override
public boolean canItBeCancelled() {
    return true;
}
    
}
