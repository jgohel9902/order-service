package com.example.order_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")

public class Product {

    //-----------------------PRODUCT VARIABLES-----------------------//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private Float price;
    private String sku;
    private Boolean isActive;
    private String image;
    private Integer stock;

    //-----------------------CONSTRUCTORS-----------------------//
    //Empty Constructor
    public Product() {}

    //General constructor (no need to set ID)
    public Product(String title, String description, Float price, String sku, Boolean isActive, String image, Integer stock) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.sku = sku;
        this.isActive = isActive;
        this.image = image;
        this.stock = stock;
    }

    //-----------------------GETTERS AND SETTERS-----------------------//


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
