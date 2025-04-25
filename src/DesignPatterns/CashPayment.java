package DesignPatterns;

import MainClasses.*;


public class CashPayment implements PaymentStrategy 
{
    @Override 
   public void pay (Order order, Client client)
   {
       System.out.println("Order of client  " + client.getName () );
       System.out.println("With order id:   " + order.getOrderID() );
       System.out.println("Amount due  " + order.calculateTotalPrice() + "To be Payed by" + getMethod() );
       
   }
    public String getMethod()
    {
        return "Cash";
    }
    
}
