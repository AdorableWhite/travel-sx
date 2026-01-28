package com.example.backend.service.application;

import com.example.backend.service.domain.model.Attraction;
import com.example.backend.service.domain.model.FoodItem;
import com.example.backend.service.domain.model.Topic;
import com.example.backend.service.domain.model.TravelNote;
import com.example.backend.service.domain.repository.AttractionRepository;
import com.example.backend.service.domain.repository.FoodRepository;
import com.example.backend.service.domain.repository.TopicRepository;
import com.example.backend.service.domain.repository.TravelNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeQueryService {

    private final AttractionRepository attractionRepository;
    private final FoodRepository foodRepository;
    private final TopicRepository topicRepository;
    private final TravelNoteRepository travelNoteRepository;

    public List<Attraction> hotAttractions(int limit) {
        return attractionRepository.list(limit);
    }

    public List<FoodItem> hotFoods(int limit) {
        return foodRepository.list(limit);
    }

    public List<Topic> hotTopics(int limit) {
        return topicRepository.list(limit);
    }

    public List<TravelNote> hotNotes(int limit) {
        return travelNoteRepository.list(limit);
    }
}
