package com.example.backend.api.controller;

import com.example.backend.common.result.R;
import com.example.backend.service.application.TopicService;
import com.example.backend.service.domain.model.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topics")
@CrossOrigin
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public R<List<Topic>> list(@RequestParam(defaultValue = "10") int limit) {
        return R.ok(topicService.list(limit));
    }

    @GetMapping("/{id}")
    public R<Topic> detail(@PathVariable Long id) {
        return R.ok(topicService.detail(id));
    }
}
