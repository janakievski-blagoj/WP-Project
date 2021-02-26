package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.exceptions.InvalidCategoryIdExceptions;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category create(String name, String description) {
        if( name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category category=new Category(name,description);
        categoryRepo.save(category);
        return category;
    }

    @Override
    public Category update(String name, String description) {
        if( name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category category=new Category(name,description);
        categoryRepo.save(category);
        return category;
    }

    @Override
    public void delete(String name) {
        if( name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        categoryRepo.deleteByName(name);

    }

    @Override
    public List<Category> listCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepo.findAllByNameLike(searchText);
    }

    @Override
    public Category deleteById(Long id) {
        Category category=this.categoryRepo.findById(id).orElseThrow(InvalidCategoryIdExceptions::new);
        categoryRepo.delete(category);
        return category;
    }
}
