package com.example.backend.service.domain.repository;

import com.example.backend.service.domain.model.FoodItem;

import java.util.List;

public interface FoodRepository {
    List<FoodItem> list(int limit);

    List<FoodItem> search(String keyword, int limit);

    FoodItem findById(Long id);
}
