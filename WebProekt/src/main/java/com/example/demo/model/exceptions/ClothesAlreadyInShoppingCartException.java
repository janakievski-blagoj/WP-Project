package com.example.demo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ClothesAlreadyInShoppingCartException extends RuntimeException{

    public ClothesAlreadyInShoppingCartException(Long id, String username) {
        super(String.format("Article with id: %d already exists in shopping cart for user with username %s", id, username));
    }
}
