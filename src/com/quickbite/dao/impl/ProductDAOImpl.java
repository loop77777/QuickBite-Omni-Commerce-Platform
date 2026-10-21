package com.quickbite.dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.quickbite.dao.GenericDAO;
import com.quickbite.entity.Product;

public class ProductDAOImpl implements GenericDAO<Product> {
    private Map<Integer, Product> productMap = new ConcurrentHashMap<>();

    public void save(Product entity) {
        productMap.put(entity.getProductId(), entity);
    }

    public void update(Product entity) {
        productMap.put(entity.getProductId(), entity);
    }

    public void delete(int id) {
        productMap.remove(id);
    }

    public Product findById(int id) {
        return productMap.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }
}