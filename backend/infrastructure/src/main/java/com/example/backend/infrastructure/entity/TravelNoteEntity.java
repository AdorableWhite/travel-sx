package com.example.backend.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("travel_note")
public class TravelNoteEntity {
    @TableId(type = IdType.AUTO)
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
