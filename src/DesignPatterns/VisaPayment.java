package DesignPatterns;
import MainClasses.Client;
import MainClasses.Order;

public class VisaPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;

    public VisaPayment(String cardNumber, String cardHolderName, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
    }
    @Override 
   public void pay (Order order, Client client)
   {
       System.out.println("Order of client  " + client.getName () );
       System.out.println("With order id:   " + order.getOrderID() );
       System.out.println("Amount due  " + order.calculateTotalPrice() + "To be Payed by" + getMethod() );
       
   }
    public String getMethod()
    {
        return "Visa";
    }
    

}
