package com.quickbite.builder;

// File: com/quickbite/builder/CustomerBuilder.java


import com.quickbite.entity.Address;
import com.quickbite.entity.Customer;

public class CustomerBuilder {
    private int userId;
    private String name;
    private String email;
    private Address address;
    private String city;
    private boolean premium;

    public CustomerBuilder setUserId(int userId) { this.userId = userId; return this; }
    public CustomerBuilder setName(String name) { this.name = name; return this; }
    public CustomerBuilder setEmail(String email) { this.email = email; return this; }
    public CustomerBuilder setAddress(Address address) { this.address = address; return this; }
    public CustomerBuilder setCity(String city) { this.city = city; return this; }
    public CustomerBuilder setPremium(boolean premium) { this.premium = premium; return this; }

    public Customer build() {
        return new Customer(userId, name, email, address, city, premium);
    }
}