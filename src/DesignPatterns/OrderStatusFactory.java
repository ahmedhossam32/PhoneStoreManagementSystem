package DesignPatterns;

public class OrderStatusFactory {
    public static OrderStatus fromString(String status) {
        if (status == null) return new PendingState(); // default fallback

        switch (status.toLowerCase()) {
            case "pending":
                return new PendingState();
            case "confirmed":
                return new ConfirmedState();
            case "shipped":
                return new ShippedState();
            case "delivered":
                return new DeliveredState();
            default:
                return new PendingState(); // fallback for unknown values
        }
    }
}
