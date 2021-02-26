package com.example.demo.repository;

import com.example.demo.model.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClothesRepo extends JpaRepository<Clothes,Long> {
    Optional<Clothes> findByName(String name);
    void deleteByName(String name);
}
