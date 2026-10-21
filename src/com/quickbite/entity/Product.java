package com.quickbite.entity;

// File: com/quickbite/entity/Product.java


public class Product {
    private int productId;
    private String name;
    private double price;
    private int stock;
    private Category category;

    public Product(int productId, String name, double price, int stock, Category category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public Category getCategory() { return category; }

    public void setStock(int stock) { this.stock = stock; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("SKU #%d: %-20s | Cat: %-12s | Price: ₹%.2f | Stock: %3d",
                productId, name, category.getCategoryName(), price, stock);
    }
}