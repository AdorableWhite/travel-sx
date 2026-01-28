package com.example.backend.service.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.infrastructure.entity.ItineraryDayEntity;
import com.example.backend.infrastructure.entity.ItineraryEntity;
import com.example.backend.infrastructure.entity.ItineraryItemEntity;
import com.example.backend.infrastructure.mapper.ItineraryDayMapper;
import com.example.backend.infrastructure.mapper.ItineraryItemMapper;
import com.example.backend.infrastructure.mapper.ItineraryMapper;
import com.example.backend.service.domain.model.Itinerary;
import com.example.backend.service.domain.model.ItineraryDay;
import com.example.backend.service.domain.model.ItineraryItem;
import com.example.backend.service.domain.repository.ItineraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ItineraryRepositoryImpl implements ItineraryRepository {

    private final ItineraryMapper itineraryMapper;
    private final ItineraryDayMapper itineraryDayMapper;
    private final ItineraryItemMapper itineraryItemMapper;

    @Override
    public List<Itinerary> listByUser(Long userId) {
        QueryWrapper<ItineraryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).orderByDesc("id");
        return itineraryMapper.selectList(wrapper).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Itinerary findById(Long id) {
        ItineraryEntity entity = itineraryMapper.selectById(id);
        if (Objects.isNull(entity)) {
            return null;
        }
        return toDomain(entity);
    }

    @Override
    public Itinerary save(Itinerary itinerary) {
        ItineraryEntity entity = toEntity(itinerary);
        if (entity.getId() == null) {
            itineraryMapper.insert(entity);
        } else {
            itineraryMapper.updateById(entity);
        }
        return toDomain(entity);
    }

    @Override
    public List<ItineraryDay> listDays(Long itineraryId) {
        QueryWrapper<ItineraryDayEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("itinerary_id", itineraryId).orderByAsc("day_index");
        return itineraryDayMapper.selectList(wrapper).stream()
                .map(this::toDayDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItineraryItem> listItems(Long itineraryDayId) {
        QueryWrapper<ItineraryItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("itinerary_day_id", itineraryDayId).orderByAsc("sort_order");
        return itineraryItemMapper.selectList(wrapper).stream()
                .map(this::toItemDomain)
                .collect(Collectors.toList());
    }

    private Itinerary toDomain(ItineraryEntity entity) {
        Itinerary domain = new Itinerary();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }

    private ItineraryEntity toEntity(Itinerary itinerary) {
        ItineraryEntity entity = new ItineraryEntity();
        BeanUtils.copyProperties(itinerary, entity);
        return entity;
    }

    private ItineraryDay toDayDomain(ItineraryDayEntity entity) {
        ItineraryDay domain = new ItineraryDay();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }

    private ItineraryItem toItemDomain(ItineraryItemEntity entity) {
        ItineraryItem domain = new ItineraryItem();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }
}
