package com.example.order_service.controller;

import com.example.order_service.entity.Product;
import com.example.order_service.repository.ProductClient;
import com.example.order_service.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    //Define the movie service for the controller to use
    //(Instead of using the repository directly)
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //-----------------------ORDER OPERATIONS-----------------------//
    //Get all products
    @GetMapping("/get-products")
    public List<Product> getProducts() {
        return orderService.GetAllProducts();
    }

    //Display the checkout
    //-----------------------COME BACK TO UPDATE THIS!!!-----------------------
    //I don't know what variable will be passed into the order service
    //I'm assuming it will be a list of products
    //This is taking the cart from the previous page and returning it for display in the orders page
    @GetMapping("/checkout")
    public List<Product> checkout(List<Product> cart){
        return cart;
    }

    //Display the completed checkout
    @GetMapping("/checkout-complete")
    public String checkoutComplete(List<Product> cart){
        if (cart.isEmpty()){
            return "Cannot checkout an empty cart!";
        }
        else{
            return orderService.CompleteOrder(cart);
        }
    }

    //Basic test URL
    @GetMapping("/test")
    public String testProducts(){
        return "List of products from order-service";
    }
}
