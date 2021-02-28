package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.exceptions.InvalidArgumentsException;
import com.example.demo.model.exceptions.InvalidUserCredentialsException;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;

    public AuthServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepo.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }
}
