package com.example.backend.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@TableName("itinerary_item")
public class ItineraryItemEntity {
    @TableId(type = IdType.AUTO)
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
