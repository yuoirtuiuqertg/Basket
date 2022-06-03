package com.example.basket.repositories;



import com.example.basket.models.Basket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepositories extends JpaRepository<Basket,Long> {
    List<Basket> findByName(String name);
}