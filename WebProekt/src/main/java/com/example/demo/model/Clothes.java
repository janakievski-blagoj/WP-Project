package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Clothes {

    @Id
    @GeneratedValue
    Long id;
    private String name;

    private Double price;

    private Integer quantity;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Manufacturer manufacturer;

    public Clothes() {
    }

    public Clothes(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}


