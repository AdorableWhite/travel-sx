package com.example.backend.service.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.infrastructure.entity.TopicEntity;
import com.example.backend.infrastructure.mapper.TopicMapper;
import com.example.backend.service.domain.model.Topic;
import com.example.backend.service.domain.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TopicRepositoryImpl implements TopicRepository {

    private final TopicMapper topicMapper;

    @Override
    public List<Topic> list(int limit) {
        QueryWrapper<TopicEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").last("limit " + limit);
        return topicMapper.selectList(wrapper).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Topic findById(Long id) {
        TopicEntity entity = topicMapper.selectById(id);
        if (Objects.isNull(entity)) {
            return null;
        }
        return toDomain(entity);
    }

    private Topic toDomain(TopicEntity entity) {
        Topic domain = new Topic();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }
}
