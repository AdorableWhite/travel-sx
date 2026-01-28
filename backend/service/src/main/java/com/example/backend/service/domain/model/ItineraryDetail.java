package com.example.backend.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDetail {
    private Itinerary itinerary;
    private List<ItineraryDayDetail> days;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItineraryDayDetail {
        private ItineraryDay day;
        private List<ItineraryItem> items;
    }
}
