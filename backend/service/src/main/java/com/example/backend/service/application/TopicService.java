package com.example.backend.service.application;

import com.example.backend.service.domain.model.Topic;
import com.example.backend.service.domain.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public List<Topic> list(int limit) {
        return topicRepository.list(limit);
    }

    public Topic detail(Long id) {
        return topicRepository.findById(id);
    }
}
