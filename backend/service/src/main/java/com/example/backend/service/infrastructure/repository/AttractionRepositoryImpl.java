package com.example.backend.service.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.infrastructure.entity.AttractionEntity;
import com.example.backend.infrastructure.mapper.AttractionMapper;
import com.example.backend.service.domain.model.Attraction;
import com.example.backend.service.domain.repository.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AttractionRepositoryImpl implements AttractionRepository {

    private final AttractionMapper attractionMapper;

    @Override
    public List<Attraction> list(int limit) {
        QueryWrapper<AttractionEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").last("limit " + limit);
        return attractionMapper.selectList(wrapper).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Attraction> search(String keyword, int limit) {
        QueryWrapper<AttractionEntity> wrapper = new QueryWrapper<>();
        if (keyword != null && !keyword.isBlank()) {
            wrapper.like("name", keyword)
                    .or()
                    .like("city", keyword)
                    .or()
                    .like("intro", keyword);
        }
        wrapper.orderByDesc("id").last("limit " + limit);
        return attractionMapper.selectList(wrapper).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Attraction findById(Long id) {
        AttractionEntity entity = attractionMapper.selectById(id);
        if (Objects.isNull(entity)) {
            return null;
        }
        return toDomain(entity);
    }

    private Attraction toDomain(AttractionEntity entity) {
        Attraction domain = new Attraction();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }
}
