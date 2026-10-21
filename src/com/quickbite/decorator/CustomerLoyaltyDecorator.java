package com.quickbite.decorator;

// File: com/quickbite/decorator/CustomerLoyaltyDecorator.java


import com.quickbite.entity.Customer;

public class CustomerLoyaltyDecorator {
    private Customer customer;

    public CustomerLoyaltyDecorator(Customer customer) {
        this.customer = customer;
    }

    public double calculateDiscount(double originalAmount) {
        if (customer.isPremium()) {
            System.out.println("  [DECORATOR] Applied 15% Premium Loyalty Discount for " + customer.getName());
            return originalAmount * 0.85;
        }
        return originalAmount;
    }
}