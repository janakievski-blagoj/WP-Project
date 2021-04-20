package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.enumerations.Role;
import com.example.demo.model.exceptions.InvalidUsernameOrPasswordException;
import com.example.demo.model.exceptions.PasswordsDoNotMatchException;
import com.example.demo.model.exceptions.UsernameAlreadyExistsException;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepo.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname, role);
        return userRepo.save(user);

    }
}
