package com.example.backend.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDay {
    private Long id;
    private Long itineraryId;
    private Integer dayIndex;
    private String summary;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
