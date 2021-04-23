package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.Clothes;
import com.example.demo.model.Manufacturer;
import com.example.demo.model.exceptions.*;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ClothesRepo;
import com.example.demo.repository.ManufacturerRepo;
import com.example.demo.service.ClothesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClothesServiceImpl implements ClothesService {
    private final ClothesRepo clothesRepo;
    private final ManufacturerRepo manufacturerRepository;
    private final CategoryRepo categoryRepository;


    public ClothesServiceImpl(ClothesRepo clothesRepo, ManufacturerRepo manufacturerRepository, CategoryRepo categoryRepo) {
        this.clothesRepo = clothesRepo;
        this.manufacturerRepository=manufacturerRepository;
        this.categoryRepository=categoryRepo;
    }


    @Override
    public List<Clothes> findAll() {
        return clothesRepo.findAll();
    }

    @Override
    public Optional<Clothes> findById(Long id) {
        return clothesRepo.findById(id);
    }

    @Override
    public Optional<Clothes> findByName(String name) {
        return clothesRepo.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Clothes> save(String name, Double price, String color, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        this.clothesRepo.deleteByName(name);
        return Optional.of(this.clothesRepo.save(new Clothes(name, price, color, quantity, category, manufacturer)));

    }

    @Override
    @Transactional
    public Optional<Clothes> edit(Long id, String name, Double price, String color, Integer quantity, Long categoryId, Long manufacturerId) {

        Clothes clothing = this.clothesRepo.findById(id).orElseThrow(() -> new ClothesNotFoundException(id));

        clothing.setName(name);
        clothing.setPrice(price);
        clothing.setColor(color);
        clothing.setQuantity(quantity);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        clothing.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        clothing.setManufacturer(manufacturer);

        return Optional.of(this.clothesRepo.save(clothing));
    }

    @Override
    public void deleteById(Long id) {
        this.clothesRepo.deleteById(id);
    }
}
