package com.example.demo.web.controller;

import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest request,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String username = request.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("clothes", this.shoppingCartService.listAllClothesInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";
    }


    @PostMapping("/add-article/{id}")
    public String addArticleToShoppingCart(@PathVariable Long id, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.shoppingCartService.addProductToShoppingCart(user.getUsername(), id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }




}
