package com.example.backend.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attraction {
    private Long id;
    private String name;
    private String level;
    private String category;
    private String city;
    private String address;
    private String openTime;
    private BigDecimal ticketPrice;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String intro;
    private String coverImage;
    private String audioUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
