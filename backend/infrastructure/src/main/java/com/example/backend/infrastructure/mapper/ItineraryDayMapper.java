package com.example.backend.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.infrastructure.entity.ItineraryDayEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItineraryDayMapper extends BaseMapper<ItineraryDayEntity> {
}
