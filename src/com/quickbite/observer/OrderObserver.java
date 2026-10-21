package com.quickbite.observer;

// File: com/quickbite/observer/OrderObserver.java

import com.quickbite.entity.Order;

public interface OrderObserver {
    void update(Order order);
}