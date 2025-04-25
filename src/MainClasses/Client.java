package MainClasses;
import DesignPatterns.*;
import java.util.List;

public class Client extends User implements Observer {
    private int clientID;
    private PhoneStore phoneStore;
    private Order orderByClient;
    
    public Client(String username, String password, String name, String email, PhoneStore phoneStore) {
        super(username, password, name, email);
        this.phoneStore = phoneStore;
    }
    @Override
    public String getRole() {
        return "Client";
    }
    @Override
    public void updatePhoneAvailability(String modelName) {
        System.out.println("Hello " + getName() + ", the phone model '" + modelName + "' is now available!");
    }

    @Override
    public void updateOrderStatus(String statusMessage) {
        System.out.println("Hello " + getName() + ", " + statusMessage);
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public Order getOrderByClient() {
        return orderByClient;
    }

    public void setOrderByClient(Order orderByClient) {
        this.orderByClient = orderByClient;
    }
   
    
    public void payForOrder(Order order , PaymentStrategy strategy) {
      PaymentService paymentService = new PaymentService();
      paymentService.pay(this, order, strategy);

}
    public PhoneStore getPhoneStore() {
        return phoneStore;
    }
    public void setPhoneStore(PhoneStore phoneStore) {
        this.phoneStore = phoneStore;
    }
    public List<Phone>browsePhones() {
        return phoneStore.getAllPhones();
    }
    public List<Phone>searchPhones(String keyword) {
        return phoneStore.searchPhones(keyword);
    }
   
  public Order placeOrder(Phone phone, int quantity, String method) {
    return phoneStore.getOrderManager().createOrder(this, phone, quantity, method);
}
   
    public void cancelOrder() {
        phoneStore.getOrderManager().cancelOrder(orderByClient.getOrderID());
    }
    public void requestPhone(String modelName) {
        phoneStore.requestPhone(this, modelName);
    }
    public void printYourOrder(Order order)
    {
         phoneStore.getOrderManager().printOrderReport(this, order);
    }
    public void viewOrderHistory() {
    List<Order> orders = phoneStore.getOrderManager().getOrdersByClient(this);

    System.out.println("📦 Order History for " + getName());
    System.out.println("-------------------------------------------------");

    for (Order order : orders) {
        System.out.println("Order ID: " + order.getOrderID());
        System.out.println("Phone: " + order.getPhone().getModelName());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.println("Total: $" + order.calculateTotalPrice());
        System.out.println("Date: " + order.getOrderDate());
        System.out.println("Status: " + order.getorderStatus());
        System.out.println("-------------------------------------");
    }
}


    public String getOrderStatus()
    {
     Order order=phoneStore.getOrderManager().getOrderById(orderByClient.getOrderID());
     return order.getorderStatus();
    }

    @Override
    public String toString() {
        return "Client{" + "clientID=" + clientID + ", phoneStore=" + phoneStore + ", orderByClient=" + orderByClient + '}';
    }
 
    
}
