package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Clothes> clothes;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.clothes = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}

