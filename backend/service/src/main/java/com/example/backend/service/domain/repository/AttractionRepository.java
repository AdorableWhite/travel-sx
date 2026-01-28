package com.example.backend.service.domain.repository;

import com.example.backend.service.domain.model.Attraction;

import java.util.List;

public interface AttractionRepository {
    List<Attraction> list(int limit);

    List<Attraction> search(String keyword, int limit);

    Attraction findById(Long id);
}
