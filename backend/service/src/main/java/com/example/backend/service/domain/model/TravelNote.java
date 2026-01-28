package com.example.backend.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelNote {
    private Long id;
    private Long userId;
    private Long topicId;
    private String title;
    private String content;
    private String coverImage;
    private Integer likeCount;
    private Integer favoriteCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
