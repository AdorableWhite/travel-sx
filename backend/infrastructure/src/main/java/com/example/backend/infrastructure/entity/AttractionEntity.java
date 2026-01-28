package com.example.backend.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("attraction")
public class AttractionEntity {
    @TableId(type = IdType.AUTO)
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
