package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Manufacturer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String address;

    public Manufacturer() {
    }

    public Manufacturer(String name, String address) {
        this.name = name;
        this.address = address;
    }
}

