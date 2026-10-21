package com.quickbite.observer;

// File: com/quickbite/observer/EmailNotificationObserver.java

import com.quickbite.entity.Order;

public class EmailNotificationObserver implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("  [EMAIL NOTIFICATION] Dispatched email to " + order.getCustomer().getEmail() + " for Order #" + order.getOrderId());
    }
}