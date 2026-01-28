package com.example.backend.service.application;

import com.example.backend.service.domain.model.FoodItem;
import com.example.backend.service.domain.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public List<FoodItem> list(int limit) {
        return foodRepository.list(limit);
    }

    public FoodItem detail(Long id) {
        return foodRepository.findById(id);
    }
}
