package DesignPatterns;
import MainClasses.*;

public interface PaymentStrategy {
    void pay (Order order, Client client);
    String getMethod();
    
    
}
