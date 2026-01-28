package com.example.backend.service.domain.repository;

import com.example.backend.service.domain.model.TravelNote;

import java.util.List;

public interface TravelNoteRepository {
    List<TravelNote> list(int limit);

    List<TravelNote> listByTopic(Long topicId, int limit);

    List<TravelNote> search(String keyword, int limit);

    TravelNote findById(Long id);

    TravelNote save(TravelNote note);
}
