package com.example.backend.api.controller;

import com.example.backend.common.result.R;
import com.example.backend.service.application.FoodService;
import com.example.backend.service.domain.model.FoodItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foods")
@CrossOrigin
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public R<List<FoodItem>> list(@RequestParam(defaultValue = "10") int limit) {
        return R.ok(foodService.list(limit));
    }

    @GetMapping("/{id}")
    public R<FoodItem> detail(@PathVariable Long id) {
        return R.ok(foodService.detail(id));
    }
}
