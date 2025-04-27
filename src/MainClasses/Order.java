package MainClasses;

import DesignPatterns.*;
import java.time.LocalDateTime;

public class Order {
  

    private int orderID;
    private Phone phone;
    private Client client;
    private int quantity;
    private OrderStatus orderstatus;
    private LocalDateTime orderDate;
    private Payment payment;
    private Observer observer;
    private boolean statusNotified;

    
public Order(Phone phone, Client client, int quantity, LocalDateTime orderDate) {
    this.phone = phone;
    this.client = client;
    this.quantity = quantity;
    this.orderDate = orderDate;
    this.orderstatus = new PendingState(); 
    this.observer = client;
    this.orderID = 0; 
    this.statusNotified = false;

}

public Order(int orderID, Phone phone, Client client, int quantity, LocalDateTime orderDate) {
    this(phone, client, quantity, orderDate); 
    this.orderID = orderID; 
    this.statusNotified = false;

}
public void setStatusNotified(boolean statusNotified) {
    this.statusNotified = statusNotified;
}

public boolean isStatusNotified() {
    return statusNotified;
}

    public OrderStatus getStatus() {
        return orderstatus;
    }

    public String getorderStatus() {
        return orderstatus.getStatus();
    }

    public void setOrderstatus(OrderStatus orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Payment getPayment() {
        return payment;
    }
    

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    public void setOrderID(int orderID) {
    this.orderID = orderID;
}

    public int getOrderID() {
        
        return orderID;
    }

    public Phone getPhone() {
        return phone;
    }

    public Client getClient() {
        return client;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public double calculateTotalPrice() {
        return phone.getPrice() * quantity;
    }
    public void nextStatus() {
        orderstatus.nextStatus(this); 
        if (observer != null) {
           observer.updateOrderStatus("Your order #" + orderID + " status changed to: " + orderstatus.getStatus());
        }
    }
}
