package com.quickbite.service;


import java.util.List;

import com.quickbite.dao.impl.OrderDAOImpl;
import com.quickbite.entity.Customer;
import com.quickbite.entity.Order;
import com.quickbite.entity.OrderItem;
import com.quickbite.entity.OrderStatus;
import com.quickbite.observer.OrderSubject;
import com.quickbite.strategy.Payment;

public class OrderService {

    private OrderDAOImpl orderDAO;

    private InventoryService inventoryService;
    private OrderSubject orderSubject;

    //OrderService orderService = new OrderService(orderDAO, inventoryService, orderSubject);

    public OrderService(OrderDAOImpl orderDAO, InventoryService inventoryService, OrderSubject orderSubject) {
        //super();
        this.orderDAO = orderDAO;
        this.inventoryService = inventoryService;
        this.orderSubject = orderSubject;
    }

    public Order createOrder(int orderId,Customer customer,List<OrderItem>items,Payment payment) {
        for(OrderItem item:items) {
            inventoryService.deductStock(item.getProduct().getProductId(),item.getQuantity());

        }

        Order order=new Order(orderId,customer,items,payment);
        order.setStatus(OrderStatus.COMPLETED);
        orderDAO.save(order);

        orderSubject.notifyObservers(order);
        return order;
    }

    public List<Order>getAllOrders(){
        return orderDAO.findAll();
    }
}