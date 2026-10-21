package com.quickbite.service;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.quickbite.collection.MaxHeap;
import com.quickbite.dao.impl.OrderDAOImpl;
import com.quickbite.entity.Order;
import com.quickbite.entity.OrderItem;
import com.quickbite.entity.OrderStatus;
import com.quickbite.entity.Product;

public class ReportService {

    private OrderDAOImpl orderDAO;

    public ReportService(OrderDAOImpl orderDAO) {
        this.orderDAO = orderDAO;

    }

    public double calculateTotalRevenue() {
        return orderDAO.findAll().stream().filter(o -> o.getStatus() == OrderStatus.COMPLETED)
                .mapToDouble(Order::getTotalAmount).reduce(0.0, Double::sum);

    }

    public Map<String, Long> getCategoryCountReport() {
        return orderDAO.findAll().stream().flatMap(o -> o.getItems().stream()).collect(Collectors
                .groupingBy(item -> item.getProduct().getCategory().getCategoryName(), Collectors.counting()));

    }

    public Product getTopSellingProduct() {
        Map<Product, Integer> salesMap = new HashMap<>();

        // Aggregate quantities across all orders before selecting the maximum with the heap.
        for (Order o : orderDAO.findAll()) {
            for (OrderItem item : o.getItems()) {
                salesMap.merge(item.getProduct(), item.getQuantity(), Integer::sum);
            }
        }

        MaxHeap maxHeap = new MaxHeap();
        salesMap.forEach(maxHeap::insert);

        MaxHeap.ProductEntry maxEntry = maxHeap.extractMax();

        return maxEntry != null ? maxEntry.product : null;

    }

}