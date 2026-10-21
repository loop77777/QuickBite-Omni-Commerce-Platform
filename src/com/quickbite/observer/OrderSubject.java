package com.quickbite.observer;
// File: com/quickbite/observer/OrderSubject.java

import com.quickbite.entity.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderSubject {
    private List<OrderObserver> observers = new ArrayList<>();

    public void attach(OrderObserver observer) { observers.add(observer); }

    public void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.update(order);
        }
    }
}