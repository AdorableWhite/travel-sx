package com.example.backend.api.controller;

import com.example.backend.common.result.R;
import com.example.backend.service.application.TravelNoteService;
import com.example.backend.service.domain.model.TravelNote;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
@CrossOrigin
@RequiredArgsConstructor
public class TravelNoteController {

    private final TravelNoteService travelNoteService;

    @GetMapping
    public R<List<TravelNote>> list(@RequestParam(required = false) Long topicId,
                                    @RequestParam(defaultValue = "10") int limit) {
        if (topicId != null) {
            return R.ok(travelNoteService.listByTopic(topicId, limit));
        }
        return R.ok(travelNoteService.list(limit));
    }

    @GetMapping("/{id}")
    public R<TravelNote> detail(@PathVariable Long id) {
        return R.ok(travelNoteService.detail(id));
    }

    @PostMapping
    public R<TravelNote> create(@RequestBody TravelNoteCreateRequest req) {
        TravelNote note = new TravelNote();
        note.setUserId(req.getUserId());
        note.setTopicId(req.getTopicId());
        note.setTitle(req.getTitle());
        note.setContent(req.getContent());
        note.setCoverImage(req.getCoverImage());
        note.setLikeCount(0);
        note.setFavoriteCount(0);
        return R.ok(travelNoteService.save(note));
    }

    @Data
    public static class TravelNoteCreateRequest {
        private Long userId;
        private Long topicId;
        private String title;
        private String content;
        private String coverImage;
    }
}
