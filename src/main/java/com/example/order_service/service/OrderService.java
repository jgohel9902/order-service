package com.example.order_service.service;

import com.example.order_service.entity.Product;
import com.example.order_service.repository.ProductClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final ProductClient productClient;

    //Constructor
    public OrderService(ProductClient productClient) {
        this.productClient = productClient;
    }

    //-----------------------API Operations related to the order service-----------------------//
    //Return list of all products
    public List<Product> GetAllProducts(){
        return productClient.findAll();
    }

    //Get stock for specific product
    public Integer GetProductStock(Integer productId){
        //If the product doesn't exist, return 0
        //This is a failsafe
        if (!productClient.findById(productId).isPresent()){
            System.out.println("Product with given ID does not exist (GetInventory)");
            return 0;
        }
        //If the product exists, retrieve and return the product's current stock
        else{
            Product relevantProduct = productClient.findById(productId).get();
            return relevantProduct.getId();
        }
    }

    //Update inventory (Subtract from stock when product stock is put into cart
    public void SubtractStock(Integer productId){
        //If the product doesn't exist, DO NOTHING
        //This is a failsafe
        if (!productClient.findById(productId).isPresent()){
            System.out.println("Product with given ID does not exist! (SubtractStock)");
        }
        //If the product exists, subtract 1 from stock
        //To update display, please call GetInventory function!!
        else{
            Product relevantProduct = productClient.findById(productId).get();
            relevantProduct.setStock(relevantProduct.getStock() - 1);
        }
    }

    //Update inventory (Add to stock when product stock is taken out of cart)
    public void AddStock(Integer productId){
        //If the product doesn't exist, DO NOTHING
        //This is a failsafe
        if (!productClient.findById(productId).isPresent()){
            System.out.println("Product with given ID does not exist! (AddStock)");
        }
        else{
            Product relevantProduct = productClient.findById(productId).get();
            relevantProduct.setStock(relevantProduct.getStock() + 1);
        }
    }

    //Function used to enable/disable buttons
    //Stock gets disabled if there is none (0) left
    public void UpdateButtonStatus(Integer productId, Boolean buttonStatus){
        //If the product doesn't exist, DO NOTHING
        //This is a failsafe
        if (!productClient.findById(productId).isPresent()){
            System.out.println("Product with given ID does not exist! (UpdateButtonStatus)");
        }
        else{
            Product relevantProduct = productClient.findById(productId).get();
            if (relevantProduct.getStock() > 0){
                //Enable the button
                buttonStatus = true;
            }
            else{
                //disable the button
                buttonStatus = false;
            }
        }
    }

    //Update all inventory and update the order service
    public String CompleteOrder(List<Product> cart){
        //Subtract stock from items in the cart and update the database
        for (Product product : cart){
            SubtractStock(product.getId());
            productClient.save(product);
        }

        //Return the delivery status to a made up location
        //This is arbitrary
        return "Ordered products being delivered to A1B 2C3 Kitchener, Ontario.";
    }
}
