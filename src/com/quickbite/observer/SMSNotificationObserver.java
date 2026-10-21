package com.quickbite.observer;

// File: com/quickbite/observer/SMSNotificationObserver.java

import com.quickbite.entity.Order;

public class SMSNotificationObserver implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("  [SMS NOTIFICATION] Sent SMS alert to " + order.getCustomer().getName() + " for Order #" + order.getOrderId());
    }
}