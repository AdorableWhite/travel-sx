package com.example.backend.service.application;

import com.example.backend.service.domain.model.Attraction;
import com.example.backend.service.domain.model.FoodItem;
import com.example.backend.service.domain.model.TravelNote;
import com.example.backend.service.domain.repository.AttractionRepository;
import com.example.backend.service.domain.repository.FoodRepository;
import com.example.backend.service.domain.repository.TravelNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final AttractionRepository attractionRepository;
    private final FoodRepository foodRepository;
    private final TravelNoteRepository travelNoteRepository;

    public List<Attraction> searchAttractions(String keyword, int limit) {
        return attractionRepository.search(keyword, limit);
    }

    public List<FoodItem> searchFoods(String keyword, int limit) {
        return foodRepository.search(keyword, limit);
    }

    public List<TravelNote> searchNotes(String keyword, int limit) {
        return travelNoteRepository.search(keyword, limit);
    }
}
