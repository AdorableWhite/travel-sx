package com.example.backend.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.infrastructure.entity.ItineraryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItineraryMapper extends BaseMapper<ItineraryEntity> {
}
