package com.quickbite.facade;

import java.util.List;

import com.quickbite.entity.Customer;
import com.quickbite.entity.Order;
import com.quickbite.entity.OrderItem;
import com.quickbite.exception.PaymentFailedException;
import com.quickbite.factory.PaymentFactory;
import com.quickbite.service.CartService;
import com.quickbite.service.CustomerService;
import com.quickbite.service.OrderService;
import com.quickbite.service.ProductService;
import com.quickbite.strategy.Payment;

public class OrderFacade {
    private final CustomerService customerService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;

    public OrderFacade(CustomerService customerService, ProductService productService, CartService cartService,
            OrderService orderService) {
        this.customerService = customerService;
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    /** Validates the checkout request, processes payment, creates the order, and empties the cart. */
    public Order checkout(int orderId, int customerId, String paymentType, String credential) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer #" + customerId + " not found.");
        }

        List<OrderItem> items = cartService.getCartItems();
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot check out an empty cart.");
        }
        // Resolve each cart product before accepting payment, so invalid product IDs fail early.
        for (OrderItem item : items) {
            productService.getProduct(item.getProduct().getProductId());
        }

        // Order creation deducts stock and notifies observers after payment succeeds.
        Payment payment = PaymentFactory.createPayment(paymentType, "PAY-" + orderId, cartService.getCartTotal(),
                credential);
        if (!payment.processPayment()) {
            throw new PaymentFailedException("Payment failed for order #" + orderId + ".");
        }

        Order order = orderService.createOrder(orderId, customer, items, payment);
        cartService.clearCart();
        return order;
    }
}
