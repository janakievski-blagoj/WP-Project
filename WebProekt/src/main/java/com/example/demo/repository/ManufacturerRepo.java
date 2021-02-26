package com.example.demo.repository;

import com.example.demo.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepo extends JpaRepository<Manufacturer,Long> {
}
