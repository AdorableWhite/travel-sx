package com.example.backend.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryItem {
    private Long id;
    private Long itineraryDayId;
    private Long attractionId;
    private LocalTime startTime;
    private Integer durationMinutes;
    private String note;
    private Integer sortOrder;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
