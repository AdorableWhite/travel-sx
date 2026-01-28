package com.example.backend.service.domain.repository;

import com.example.backend.service.domain.model.Topic;

import java.util.List;

public interface TopicRepository {
    List<Topic> list(int limit);

    Topic findById(Long id);
}
