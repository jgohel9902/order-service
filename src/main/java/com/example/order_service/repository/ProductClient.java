package com.example.order_service.repository;

import com.example.order_service.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name= "product-service")
public interface ProductClient extends JpaRepository<Product,Integer> {

    @GetMapping("/products/test")
    String getProducts();

}
