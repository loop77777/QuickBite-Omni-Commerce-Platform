package com.quickbite.factory;

// File: com/quickbite/factory/PaymentFactory.java

import com.quickbite.strategy.*;

public class PaymentFactory {
    public static Payment createPayment(String type, String paymentId, double amount, String credential) {
        switch (type.toUpperCase()) {
            case "CARD":
                return new CreditCardPayment(paymentId, amount, credential);
            case "UPI":
                return new UPIPayment(paymentId, amount, credential);
            case "WALLET":
                return new WalletPayment(paymentId, amount, credential);
            case "PAYPAL":
                return new PaypalAdapter(paymentId, amount, new PaypalSDK(), credential);
            default:
                throw new IllegalArgumentException("Unsupported payment mode: " + type);
        }
    }
}







