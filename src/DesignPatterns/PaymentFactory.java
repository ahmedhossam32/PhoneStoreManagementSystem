package DesignPatterns;

public class PaymentFactory {

    // Default strategy based on type (no input)
    public static PaymentStrategy createPayment(String method) {
        if (method.equalsIgnoreCase("cash")) {
            return new CashPayment();
        } else if (method.equalsIgnoreCase("visa")) {
            // fallback default (not recommended for real use)
            return new VisaPayment("0000-0000-0000-0000", "Default", "000");
        }
        return new CashPayment(); // default
    }

    // Flexible visa-specific creator
    public static PaymentStrategy createVisa(String cardNumber, String holderName, String cvv) {
        return new VisaPayment(cardNumber, holderName, cvv);
    }
}
