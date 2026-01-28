package com.example.backend.api.controller;

import com.example.backend.common.result.R;
import com.example.backend.service.application.SearchService;
import com.example.backend.service.domain.model.Attraction;
import com.example.backend.service.domain.model.FoodItem;
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
@RequestMapping("/search")
@CrossOrigin
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public R<SearchResponse> search(@RequestParam String keyword,
                                    @RequestParam(defaultValue = "6") int limit) {
        return R.ok(new SearchResponse(
                searchService.searchAttractions(keyword, limit),
                searchService.searchFoods(keyword, limit),
                searchService.searchNotes(keyword, limit)
        ));
    }

    @Data
    @AllArgsConstructor
    public static class SearchResponse {
        private List<Attraction> attractions;
        private List<FoodItem> foods;
        private List<TravelNote> notes;
    }
}
