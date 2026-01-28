package com.example.backend.service.domain.repository;

import com.example.backend.service.domain.model.Itinerary;
import com.example.backend.service.domain.model.ItineraryDay;
import com.example.backend.service.domain.model.ItineraryItem;

import java.util.List;

public interface ItineraryRepository {
    List<Itinerary> listByUser(Long userId);

    Itinerary findById(Long id);

    Itinerary save(Itinerary itinerary);

    List<ItineraryDay> listDays(Long itineraryId);

    List<ItineraryItem> listItems(Long itineraryDayId);
}
