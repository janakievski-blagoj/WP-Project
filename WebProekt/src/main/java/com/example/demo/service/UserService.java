package com.example.demo.service;

import com.example.demo.model.Clothes;
import com.example.demo.model.ShoppingCart;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {


    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
