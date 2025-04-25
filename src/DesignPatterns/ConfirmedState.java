package DesignPatterns;
import MainClasses.*;

public class ConfirmedState implements OrderStatus 
{
   @Override
     public void nextStatus(Order order) {
        order.setOrderstatus(new ShippedState()); 
        System.out.println("Order #" + order.getOrderID() + " has been Shipped.");
    }

@Override
public String getStatus() {
    return "Confirmed";
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
