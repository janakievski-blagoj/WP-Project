package com.example.demo.service.impl;

import com.example.demo.model.Clothes;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.ShoppingCartStatus;
import com.example.demo.model.exceptions.ClothesAlreadyInShoppingCartException;
import com.example.demo.model.exceptions.ClothesNotFoundException;
import com.example.demo.model.exceptions.ShoppingCartNotFoundException;
import com.example.demo.model.exceptions.UserNotFoundException;
import com.example.demo.repository.ShoppingCartRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.ClothesService;
import com.example.demo.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepo shoppingCartRepo;
    private final UserRepo userRepo;
    private final ClothesService clothesService;

    public ShoppingCartServiceImpl(ShoppingCartRepo shoppingCartRepo,
                                   UserRepo userRepo,
                                   ClothesService clothesService) {
        this.shoppingCartRepo = shoppingCartRepo;
        this.userRepo = userRepo;
        this.clothesService = clothesService;
    }

    @Override
    public List<Clothes> listAllClothesInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepo.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepo.findById(cartId).get().getClothes();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepo
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepo.save(cart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long clothesId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Clothes clothes = this.clothesService.findById(clothesId)
                .orElseThrow(() -> new ClothesNotFoundException(clothesId));
        if(shoppingCart.getClothes()
                .stream().filter(i -> i.getId().equals(clothesId))
                .collect(Collectors.toList()).size() > 0)
            throw new ClothesAlreadyInShoppingCartException(clothesId, username);
        shoppingCart.getClothes().add(clothes);
        return this.shoppingCartRepo.save(shoppingCart);
    }
}
