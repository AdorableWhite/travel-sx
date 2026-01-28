package com.example.backend.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.infrastructure.entity.ItineraryItemEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItineraryItemMapper extends BaseMapper<ItineraryItemEntity> {
}
