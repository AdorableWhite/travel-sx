package com.example.backend.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    private Long id;
    private String name;
    private String description;
    private String coverImage;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
