package com.example.backend.api.controller;

import com.example.backend.common.result.R;
import com.example.backend.service.application.HomeQueryService;
import com.example.backend.service.domain.model.Attraction;
import com.example.backend.service.domain.model.FoodItem;
import com.example.backend.service.domain.model.Topic;
import com.example.backend.service.domain.model.TravelNote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
@CrossOrigin
@RequiredArgsConstructor
public class HomeController {

    private final HomeQueryService homeQueryService;

    @GetMapping
    public R<HomeResponse> home(@RequestParam(defaultValue = "6") int limit) {
        return R.ok(new HomeResponse(
                homeQueryService.hotAttractions(limit),
                homeQueryService.hotFoods(limit),
                homeQueryService.hotTopics(limit),
                homeQueryService.hotNotes(limit)
        ));
    }

    @Data
    @AllArgsConstructor
    public static class HomeResponse {
        private List<Attraction> hotAttractions;
        private List<FoodItem> hotFoods;
        private List<Topic> hotTopics;
        private List<TravelNote> hotNotes;
    }
}
