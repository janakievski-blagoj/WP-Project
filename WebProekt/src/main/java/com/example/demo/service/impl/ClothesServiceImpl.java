package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.Clothes;
import com.example.demo.model.Manufacturer;
import com.example.demo.model.exceptions.InvalidCategoryIdExceptions;
import com.example.demo.model.exceptions.InvalidClothesIdException;
import com.example.demo.model.exceptions.InvalidManufacturerIdException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ClothesRepo;
import com.example.demo.repository.ManufacturerRepo;
import com.example.demo.service.ClothesService;
import org.springframework.stereotype.Service;

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
    public Optional<Clothes> save(String name, Double price, Integer quantity, Long categoryID, Long manufacturerID) {
        Category category = this.categoryRepository.findById(categoryID).orElseThrow(InvalidCategoryIdExceptions::new);
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerID).orElseThrow(InvalidManufacturerIdException::new);

        this.clothesRepo.deleteByName(name);
        return Optional.of(this.clothesRepo.save(new Clothes(name, price, quantity, category, manufacturer)));

    }

    @Override
    public Optional<Clothes> edit(Long id, String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Clothes piece = this.clothesRepo.findById(id).orElseThrow(InvalidClothesIdException::new);

        piece.setName(name);
        piece.setPrice(price);
        piece.setQuantity(quantity);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(InvalidCategoryIdExceptions::new);
        piece.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(InvalidManufacturerIdException::new);
        piece.setManufacturer(manufacturer);

        return Optional.of(this.clothesRepo.save(piece));

    }

    @Override
    public void deleteById(Long id) {
        this.clothesRepo.deleteById(id);
    }
}
