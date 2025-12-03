package com.example.order_service.repository;

import com.example.order_service.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "product-service",
        url = "https://productservice-i4lz.onrender.com"
)
public interface ProductClient  {

    @GetMapping("/api/products")
    List<Product> findAll();

    @GetMapping("/api/products/{id}")
    Product findById(@PathVariable("id") Integer id);

    @PostMapping("/api/products")
    Product save(@RequestBody Product product);
}
