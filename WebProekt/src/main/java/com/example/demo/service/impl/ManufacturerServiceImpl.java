package com.example.demo.service.impl;

import com.example.demo.model.Manufacturer;
import com.example.demo.repository.ManufacturerRepo;
import com.example.demo.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepo manufacturerRepo;

    public ManufacturerServiceImpl(ManufacturerRepo manufacturerRepo) {
        this.manufacturerRepo = manufacturerRepo;
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepo.findById(id);
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepo.findAll();
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return Optional.of(this.manufacturerRepo.save(new Manufacturer(name,address)));
    }

    @Override
    public void deleteById(Long id) {
        this.manufacturerRepo.deleteById(id);
    }
}
