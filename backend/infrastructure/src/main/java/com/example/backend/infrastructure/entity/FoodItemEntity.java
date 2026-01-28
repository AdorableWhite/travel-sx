package com.example.backend.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("food_item")
public class FoodItemEntity {
    @TableId(type = IdType.AUTO)
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
