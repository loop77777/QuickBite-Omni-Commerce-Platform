package com.quickbite.service;

import java.util.List;
import java.util.stream.Collectors;

import com.quickbite.builder.CustomerBuilder;
import com.quickbite.dao.impl.CustomerDAOImpl;
import com.quickbite.entity.Address;
import com.quickbite.entity.Customer;

public class CustomerService {
    private CustomerDAOImpl customerDAO;

    //CustomerService customerService = new CustomerService(customerDAO);
    public CustomerService(CustomerDAOImpl customerDAO) {
        this.customerDAO = customerDAO;
    }

	/*Address addr1 = new Address("100 Residency Rd", "Bengaluru", "Karnataka", "560025");
	Address addr2 = new Address("45 Anna Salai", "Chennai", "Tamil Nadu", "600002");

	Customer c1 = new CustomerBuilder().setUserId(1).setName("Karthik Raja").setEmail("karthik@quickbite.in")
			.setAddress(addr1).setCity("Bengaluru").setPremium(true).build();

	Customer c2 = new CustomerBuilder().setUserId(2).setName("Ananya Rao").setEmail("ananya@quickbite.in")
			.setAddress(addr2).setCity("Chennai").setPremium(false).build();

	customerService.registerCustomer(c1);
	customerService.registerCustomer(c2);*/

    public void registerCustomer(Customer customer) {
        customerDAO.save(customer);
    }

    public Customer getCustomer(int id) {
        return customerDAO.findById(id);
    }

    public List<Customer> getPremiumCustomers() {
        return customerDAO.findAll().stream().filter(Customer::isPremium).collect(Collectors.toList());
    }

}