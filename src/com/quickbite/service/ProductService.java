package com.quickbite.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.quickbite.dao.impl.ProductDAOImpl;
import com.quickbite.entity.Category;
import com.quickbite.entity.Product;
import com.quickbite.exception.ProductNotFoundException;

public class ProductService {
    private ProductDAOImpl productDAO;

    private Set<Category> categorySet = new HashSet<>();

    public ProductService(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;

    }

	/*Product p1 = new Product(101, "Sony Wireless Headphones", 12000.0, 15, electronics);
	Product p2 = new Product(102, "Hyderabadi Chicken Biryani", 350.0, 50, food);
	Product p3 = new Product(103, "Organic Olive Oil 1L", 850.0, 30, grocery);
	Product p4 = new Product(104, "Levi's Denim Jacket", 4500.0, 10, fashion);

	productService.addProduct(p1);
	productService.addProduct(p2);
	productService.addProduct(p3);
	productService.addProduct(p4);*/

    public void addProduct(Product product) {

        productDAO.save(product);
        categorySet.add(product.getCategory());

    }

    public Product getProduct(int id) {
        Product p = productDAO.findById(id);
        if (p == null)
            throw new ProductNotFoundException("Product #" + id + "not found.");
        return p;
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();

    }

    public Set<Category> getCategories() {
        return categorySet;
    }

    public List<Product> searchByName(String keyword) {
        return productDAO.findAll().stream().filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

    }
}