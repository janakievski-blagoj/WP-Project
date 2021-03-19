package com.example.demo.service;

import com.example.demo.model.Clothes;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.UserType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {


    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User register(String username, String password, String repeatPassword, String name, String surname, UserType userType);



}
