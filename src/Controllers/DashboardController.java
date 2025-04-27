package Controllers;

import Database.*;
import MainClasses.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardController {
    private PhoneStore phoneStore = PhoneStore.getInstance();
    private RequestDAO requestDAO = new RequestDAO();
    private OrderDAO orderDAO = new OrderDAO();

    public List<String> getPhoneNotifications(Client client) {
        List<String> notifications = new ArrayList<>();
        List<RequestPhone> allRequests = phoneStore.getAllRequests();
        List<Phone> phones = phoneStore.getAllPhones();

        for (RequestPhone request : allRequests) {
            if (request.getClient().getClientID() == client.getClientID()) {
                for (Phone phone : phones) {
                    if (PhoneStore.isSameModel(phone.getModelName(), request.getModelRequested())) {
                        notifications.add("📢 Good news! Your requested phone \"" + phone.getModelName() + "\" is now available!");
                        requestDAO.markAsNotified(request.getRequestID());
                        break;
                    }
                }
            }
        }
        return notifications;
    }

    public List<String> getOrderStatusUpdates(Client client) {
        List<String> updates = new ArrayList<>();
        List<Order> clientOrders = orderDAO.getOrdersByClientId(client.getClientID());

        for (Order order : clientOrders) {
            if (!order.getorderStatus().equalsIgnoreCase("Pending") && !order.isStatusNotified()) {
                updates.add("📦 Your order #" + order.getOrderID() + " status updated to: " + order.getorderStatus());
                orderDAO.markOrderAsNotified(order.getOrderID());
            }
        }
        return updates;
    }
}
