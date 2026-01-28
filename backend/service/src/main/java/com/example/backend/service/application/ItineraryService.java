package com.example.backend.service.application;

import com.example.backend.service.domain.model.Itinerary;
import com.example.backend.service.domain.model.ItineraryDetail;
import com.example.backend.service.domain.model.ItineraryItem;
import com.example.backend.service.domain.repository.ItineraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;

    public List<Itinerary> listByUser(Long userId) {
        return itineraryRepository.listByUser(userId);
    }

    public ItineraryDetail detail(Long itineraryId) {
        Itinerary itinerary = itineraryRepository.findById(itineraryId);
        if (itinerary == null) {
            return null;
        }
        List<ItineraryDetail.ItineraryDayDetail> dayDetails = itineraryRepository.listDays(itineraryId)
                .stream()
                .map(day -> {
                    List<ItineraryItem> items = itineraryRepository.listItems(day.getId());
                    return new ItineraryDetail.ItineraryDayDetail(day, items);
                })
                .collect(Collectors.toList());
        return new ItineraryDetail(itinerary, dayDetails);
    }

    public Itinerary save(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }
}
