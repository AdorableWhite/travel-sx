package com.example.backend.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {
    private Long id;
    private String name;
    private String category;
    private String city;
    private String intro;
    private String coverImage;
    private String videoUrl;
    private BigDecimal rating;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
