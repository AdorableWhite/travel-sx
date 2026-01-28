package com.example.backend.api.controller;

import com.example.backend.common.result.R;
import com.example.backend.service.application.AttractionService;
import com.example.backend.service.domain.model.Attraction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attractions")
@CrossOrigin
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping
    public R<List<Attraction>> list(@RequestParam(defaultValue = "10") int limit) {
        return R.ok(attractionService.list(limit));
    }

    @GetMapping("/{id}")
    public R<Attraction> detail(@PathVariable Long id) {
        return R.ok(attractionService.detail(id));
    }
}
