package com.example.backend.api.controller;

import com.example.backend.common.result.R;
import com.example.backend.service.application.ItineraryService;
import com.example.backend.service.domain.model.Itinerary;
import com.example.backend.service.domain.model.ItineraryDetail;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/itineraries")
@CrossOrigin
@RequiredArgsConstructor
public class ItineraryController {

    private final ItineraryService itineraryService;

    @GetMapping
    public R<List<Itinerary>> list(@RequestParam Long userId) {
        return R.ok(itineraryService.listByUser(userId));
    }

    @GetMapping("/{id}")
    public R<ItineraryDetail> detail(@PathVariable Long id) {
        return R.ok(itineraryService.detail(id));
    }

    @PostMapping
    public R<Itinerary> create(@RequestBody ItineraryCreateRequest req) {
        Itinerary itinerary = new Itinerary();
        itinerary.setUserId(req.getUserId());
        itinerary.setTitle(req.getTitle());
        itinerary.setStartDate(req.getStartDate());
        itinerary.setDays(req.getDays());
        itinerary.setStatus("DRAFT");
        return R.ok(itineraryService.save(itinerary));
    }

    @Data
    public static class ItineraryCreateRequest {
        private Long userId;
        private String title;
        private LocalDate startDate;
        private Integer days;
    }
}
