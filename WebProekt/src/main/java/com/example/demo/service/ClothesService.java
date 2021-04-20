package com.example.demo.service;

import com.example.demo.model.Clothes;

import java.util.List;
import java.util.Optional;

public interface ClothesService {
    List<Clothes> findAll();

    Optional<Clothes> findById(Long id);

    Optional<Clothes> findByName(String name);

    Optional<Clothes> save(String name, Double price, Integer quantity, Long category, Long manufacturer,String color);



    Optional<Clothes> edit(Long id, String name, Double price, Integer quantity, Long category, Long manufacturer, String color);



    void deleteById(Long id);

}
