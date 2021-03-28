package com.example.demo.service;

import com.example.demo.model.Clothes;
import com.example.demo.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Clothes> listAllClothesInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);
}
