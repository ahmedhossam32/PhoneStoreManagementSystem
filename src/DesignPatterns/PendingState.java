package DesignPatterns;
import MainClasses.*;
public class PendingState implements OrderStatus 
{
   @Override
    public void nextStatus(Order order) {
        order.setOrderstatus(new ConfirmedState()); 
        System.out.println("Order #" + order.getOrderID() + " has been confirmed.");
    }

   @Override
   public String getStatus() {
   return "Pending";
   }

@Override
public double cancellationFees(Order order) {
  
    return 0.0;
  
}

@Override
public boolean canItBeCancelled() 
{ 
    return true;
}

}
