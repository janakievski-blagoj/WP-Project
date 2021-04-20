package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {


    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);



}
