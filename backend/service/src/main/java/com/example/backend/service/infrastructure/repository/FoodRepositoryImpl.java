package com.example.backend.service.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.infrastructure.entity.FoodItemEntity;
import com.example.backend.infrastructure.mapper.FoodItemMapper;
import com.example.backend.service.domain.model.FoodItem;
import com.example.backend.service.domain.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepository {

    private final FoodItemMapper foodItemMapper;

    @Override
    public List<FoodItem> list(int limit) {
        QueryWrapper<FoodItemEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").last("limit " + limit);
        return foodItemMapper.selectList(wrapper).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<FoodItem> search(String keyword, int limit) {
        QueryWrapper<FoodItemEntity> wrapper = new QueryWrapper<>();
        if (keyword != null && !keyword.isBlank()) {
            wrapper.like("name", keyword)
                    .or()
                    .like("city", keyword)
                    .or()
                    .like("intro", keyword);
        }
        wrapper.orderByDesc("id").last("limit " + limit);
        return foodItemMapper.selectList(wrapper).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public FoodItem findById(Long id) {
        FoodItemEntity entity = foodItemMapper.selectById(id);
        if (Objects.isNull(entity)) {
            return null;
        }
        return toDomain(entity);
    }

    private FoodItem toDomain(FoodItemEntity entity) {
        FoodItem domain = new FoodItem();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }
}
