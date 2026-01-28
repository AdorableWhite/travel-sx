package com.example.backend.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {
    private Long id;
    private Long userId;
    private String title;
    private LocalDate startDate;
    private Integer days;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
