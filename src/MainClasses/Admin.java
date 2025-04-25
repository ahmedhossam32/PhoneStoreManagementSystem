package MainClasses;
import java.util.List;


public class Admin extends User {
    private String adminID;
    private PhoneStore phoneStore;

   
    public Admin(String username, String password, String name, String email, String adminID, PhoneStore phoneStore) {
        super(username, password, name, email);
        this.adminID = adminID;
        this.phoneStore = phoneStore;
    }

    @Override
    public String getRole() {
        return "Admin";
    }

   
    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public PhoneStore getPhoneStore() {
        return phoneStore;
    }

    public void setPhoneStore(PhoneStore phoneStore) {
        this.phoneStore = phoneStore;
    }

    public void addPhone(Phone phone) {
        phoneStore.addPhone(phone);
    }

    public void removePhone(String modelName) {
        phoneStore.removePhone(modelName);
    }

    public void updatePhone(String modelName, double newPrice, int newStock) {
        phoneStore.updatePhone(modelName, newPrice, newStock);
    }

    public List<Phone> viewInventory() {
        return phoneStore.getAllPhones();
    }

  
    public void printClientRequests()
    {
        System.out.println("The Requests are ");
        System.out.println("____________________________________________");
        
         List<RequestPhone> clientRequests =  getClientRequests();
         for (RequestPhone request : clientRequests) {
             System.out.println("For Client " + request.getClient().getName());
             System.out.println("With request id  " + request.getRequestID() + "At " +request.getRequestDate().getHour() );
             System.out.println("The Request was for model " + request.getModelRequested());
          
         }
    }
    public List<RequestPhone> getClientRequests() {
        
        return phoneStore.getAllRequests();
    }

    
    public List<Order> getAllOrders() {
        
        return phoneStore.getOrderManager().getAllOrders();
    }
    public void printAllOrders ()
    {
     List<Order> orders = getAllOrders();
    System.out.println("Orders of store : ");
    System.out.println("_____________________________________________");

    for (int i = 0; i < orders.size(); i++) {
        Order order = orders.get(i); // You missed this line
        System.out.println("Order number " + (i + 1));
        System.out.println("Order ID: " + order.getOrderID());
        System.out.println("Phone: " + order.getPhone().getModelName());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.println("Total: $" + order.calculateTotalPrice());
        System.out.println("Date: " + order.getOrderDate());
        System.out.println("-------------------------------------");
    }
    }
    public void updateOrderStatus(int orderID) {
    phoneStore.getOrderManager().moveOrderToNextStatus(orderID);
}

    
}