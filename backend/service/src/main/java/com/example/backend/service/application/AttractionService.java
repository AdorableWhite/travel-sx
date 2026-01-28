package com.example.backend.service.application;

import com.example.backend.service.domain.model.Attraction;
import com.example.backend.service.domain.repository.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;

    public List<Attraction> list(int limit) {
        return attractionRepository.list(limit);
    }

    public Attraction detail(Long id) {
        return attractionRepository.findById(id);
    }
}
