package DesignPatterns;

public interface Observer {
    void updatePhoneAvailability(String modelName);    
    void updateOrderStatus(String statusMessage);       
}
