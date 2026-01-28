package com.example.backend.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.infrastructure.entity.FoodItemEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodItemMapper extends BaseMapper<FoodItemEntity> {
}
