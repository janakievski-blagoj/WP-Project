package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Clothes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    private String color;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Manufacturer manufacturer;

    public Clothes() {
    }

    public Clothes(String name, Double price, String color, Integer quantity, Category category, Manufacturer manufacturer) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
    }


}


