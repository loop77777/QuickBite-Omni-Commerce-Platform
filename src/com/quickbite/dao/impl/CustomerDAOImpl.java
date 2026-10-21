package com.quickbite.dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.quickbite.dao.GenericDAO;
import com.quickbite.entity.Customer;

public class CustomerDAOImpl implements GenericDAO<Customer> {

    private Map<Integer, Customer> customerMap = new ConcurrentHashMap<>();


    public void save(Customer entity) {
        customerMap.put(entity.getUserId(), entity);
    }


    public void update(Customer entity) {
        customerMap.put(entity.getUserId(), entity);
    }


    public void delete(int id) {
        customerMap.remove(id);
    }


    public Customer findById(int id) {
        return customerMap.get(id);
    }


    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }
}