package MainClasses;

import DesignPatterns.*;
import Database.*;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneStore implements Subject {
    private static PhoneStore instance;
    private final OrderManager orderManager = new OrderManager();
    private final RequestDAO requestDAO = new RequestDAO();
    private final PhoneDAO phoneDAO = new PhoneDAO();
    private final List<Observer> observers = new java.util.ArrayList<>();

    private PhoneStore() {}

    public static PhoneStore getInstance() {
        if (instance == null) {
            instance = new PhoneStore();
        }
        return instance;
    }

    public void addPhone(Phone phone) {
        phoneDAO.addOrUpdatePhone(phone);
        notifySubscribers(phone.getModelName());
    }

    public void removePhone(String modelName) {
        phoneDAO.deletePhoneByModel(modelName);
    }

    public void updatePhone(String modelName, double newPrice, int newStock) {
        phoneDAO.updatePhoneByModel(modelName, newPrice, newStock);
    }

    public Phone getPhoneByID(int id) {
        return phoneDAO.getPhoneById(id);
    }

    public List<Phone> getAllPhones() {
        return phoneDAO.getAllPhones();
    }

    public List<Phone> searchPhones(String keyword) {
        return phoneDAO.getAllPhones().stream()
                .filter(p -> p.getModelName().toLowerCase().contains(keyword.toLowerCase())
                        || p.getBrand().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void addRequest(RequestPhone request) {
        requestDAO.addRequest(request);
        
    }

  
    public List<RequestPhone> getAllRequests() {
     
    return requestDAO.getAllUnnotifiedRequests();  // Only unnotified requests
}

    

    public static boolean isSameModel(String modelA, String modelB) {
        if (modelA == null || modelB == null) return false;
        return modelA.replaceAll("\\s+", "").equalsIgnoreCase(
                modelB.replaceAll("\\s+", ""));
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public Order requestPhone(Client client, String modelName) {
        RequestPhone request = new RequestPhone(client, modelName, java.time.LocalDateTime.now());
        addRequest(request);
        addSubscriber(client);
        return null;
    }

    @Override
    public void addSubscriber(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeSubscriber(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifySubscribers(String modelName) {
        List<RequestPhone> matchingRequests = requestDAO.getAllUnnotifiedRequests();

        for (RequestPhone request : matchingRequests) {
            Observer client = request.getClient();
            client.updatePhoneAvailability(modelName);
            
        }
    }
}