package com.example.backend.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("itinerary_day")
public class ItineraryDayEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long itineraryId;
    private Integer dayIndex;
    private String summary;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
