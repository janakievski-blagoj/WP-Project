package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(String name, String description);

    Category update(String name, String description);

    void delete(String name);
    Category deleteById(Long id);

    List<Category> listCategories();

    List<Category> searchCategories(String searchText);

}
