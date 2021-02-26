package com.example.demo.service.impl;

import com.example.demo.repository.ShoppingCartRepo;
import com.example.demo.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepo shoppingCartRepo;

    public ShoppingCartServiceImpl(ShoppingCartRepo shoppingCartRepo) {
        this.shoppingCartRepo = shoppingCartRepo;
    }

}
