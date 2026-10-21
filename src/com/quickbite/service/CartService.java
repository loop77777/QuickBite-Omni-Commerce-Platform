package com.quickbite.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quickbite.entity.Order;
import com.quickbite.entity.OrderItem;
import com.quickbite.entity.Product;

public class CartService {

    private Map<Integer, OrderItem> cartMap = new HashMap<>();

	/*
		Product p1 = new Product(101, "Sony Wireless Headphones", 12000.0, 15, electronics);
		Product p2 = new Product(102, "Hyderabadi Chicken Biryani", 350.0, 50, food);
		Product p3 = new Product(103, "Organic Olive Oil 1L", 850.0, 30, grocery);
		Product p4 = new Product(104, "Levi's Denim Jacket", 4500.0, 10, fashion);
	 *
	 * cartService.addProduct(p1, 1);
	cartService.addProduct(p2, 2);
	Order order1 = orderController.processCheckout(5001, 1, "UPI", "karthik@okaxis");
	System.out.println("   [SUCCESS] " + order1);

	System.out.println("\n4.PROCESSING ORDER #5002 (REGULAR CUSTOMER - PAYPAL ADAPTER)...");
	cartService.addProduct(p2, 4);
	cartService.addProduct(p3, 2);
	Order order2 = orderController.processCheckout(5002, 2, "PAYPAL", "PAYPAL_LIVE_TOKEN_9988");
	System.out.println("[SUCCESS] " + order2);*/

    public void addProduct(Product product, int qty) {
        if (cartMap.containsKey(product.getProductId())) {
            OrderItem existing = cartMap.get(product.getProductId());
            cartMap.put(product.getProductId(), new OrderItem(product, existing.getQuantity() + qty));

        } else {
            cartMap.put(product.getProductId(), new OrderItem(product, qty));

        }
    }

    public void removeProduct(int productId) {
        cartMap.remove(productId);
    }

    public List<OrderItem> getCartItems() {
        return new ArrayList<>(cartMap.values());

    }

    public void clearCart() {
        cartMap.clear();
    }

    public double getCartTotal() {
        return cartMap.values().stream().mapToDouble(OrderItem::getSubtotal).sum();
    }
}