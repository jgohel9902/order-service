package com.example.order_service.service;

import com.example.order_service.entity.Product;
import com.example.order_service.repository.ProductClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final ProductClient productClient;

    public OrderService(ProductClient productClient) {
        this.productClient = productClient;
    }

    // ---------------------------------------------------------
    // GET ALL PRODUCTS
    // ---------------------------------------------------------
    public List<Product> getAllProducts() {
        return productClient.findAll();
    }

    // ---------------------------------------------------------
    // GET PRODUCT STOCK
    // ---------------------------------------------------------
    public Integer getProductStock(Integer productId) {
        Product p = productClient.findById(productId);

        if (p == null) {
            System.out.println("Product not found (getProductStock)");
            return 0;
        }

        return p.getStock();
    }

    // ---------------------------------------------------------
    // SUBTRACT STOCK
    // ---------------------------------------------------------
    public void subtractStock(Integer productId) {
        Product p = productClient.findById(productId);

        if (p == null) {
            System.out.println("Product not found (subtractStock)");
            return;
        }

        if (p.getStock() > 0) {
            p.setStock(p.getStock() - 1);
            productClient.save(p);
        }
    }

    // ---------------------------------------------------------
    // ADD STOCK
    // ---------------------------------------------------------
    public void addStock(Integer productId) {
        Product p = productClient.findById(productId);

        if (p == null) {
            System.out.println("Product not found (addStock)");
            return;
        }

        p.setStock(p.getStock() + 1);
        productClient.save(p);
    }

    // ---------------------------------------------------------
    // BUTTON STATUS LOGIC (returns boolean instead of modifying a local variable)
    // ---------------------------------------------------------
    public boolean isButtonEnabled(Integer productId) {
        Product p = productClient.findById(productId);

        if (p == null) {
            System.out.println("Product not found (isButtonEnabled)");
            return false;
        }

        return p.getStock() > 0;
    }

    // ---------------------------------------------------------
    // COMPLETE ORDER
    // ---------------------------------------------------------
    public String completeOrder(List<Product> cart) {

        for (Product item : cart) {

            Product p = productClient.findById(item.getId());

            if (p == null) {
                System.out.println("Item missing in product-service (completeOrder)");
                continue;
            }

            if (p.getStock() > 0) {
                p.setStock(p.getStock() - 1);
                productClient.save(p);
            }
        }

        return "Order placed successfully! Delivery to Kitchener, Ontario.";
    }
}

