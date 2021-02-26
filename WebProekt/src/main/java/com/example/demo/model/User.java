package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    private String username;

    private String password;

    private String name;

    private String surname;



    @Enumerated(value = EnumType.STRING)
    private UserType userType;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    public User() {
    }

    public User(String username, String password, String name, String surname, UserType userType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userType = userType;
    }


}
