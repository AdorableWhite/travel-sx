package com.example.backend.service.application;

import com.example.backend.service.domain.model.TravelNote;
import com.example.backend.service.domain.repository.TravelNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelNoteService {

    private final TravelNoteRepository travelNoteRepository;

    public List<TravelNote> list(int limit) {
        return travelNoteRepository.list(limit);
    }

    public List<TravelNote> listByTopic(Long topicId, int limit) {
        return travelNoteRepository.listByTopic(topicId, limit);
    }

    public TravelNote detail(Long id) {
        return travelNoteRepository.findById(id);
    }

    public TravelNote save(TravelNote note) {
        return travelNoteRepository.save(note);
    }
}
