package com.example.demo.service.impl;

import com.example.demo.model.Clothes;
import com.example.demo.model.ShoppingCart;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder=passwordEncoder;
    }


}
