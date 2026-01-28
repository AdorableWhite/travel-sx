package com.example.backend.service.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.infrastructure.entity.TravelNoteEntity;
import com.example.backend.infrastructure.mapper.TravelNoteMapper;
import com.example.backend.service.domain.model.TravelNote;
import com.example.backend.service.domain.repository.TravelNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TravelNoteRepositoryImpl implements TravelNoteRepository {

    private final TravelNoteMapper travelNoteMapper;

    @Override
    public List<TravelNote> list(int limit) {
        QueryWrapper<TravelNoteEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").last("limit " + limit);
        return travelNoteMapper.selectList(wrapper).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<TravelNote> listByTopic(Long topicId, int limit) {
        QueryWrapper<TravelNoteEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("topic_id", topicId).orderByDesc("id").last("limit " + limit);
        return travelNoteMapper.selectList(wrapper).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<TravelNote> search(String keyword, int limit) {
        QueryWrapper<TravelNoteEntity> wrapper = new QueryWrapper<>();
        if (keyword != null && !keyword.isBlank()) {
            wrapper.like("title", keyword)
                    .or()
                    .like("content", keyword);
        }
        wrapper.orderByDesc("id").last("limit " + limit);
        return travelNoteMapper.selectList(wrapper).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public TravelNote findById(Long id) {
        TravelNoteEntity entity = travelNoteMapper.selectById(id);
        if (Objects.isNull(entity)) {
            return null;
        }
        return toDomain(entity);
    }

    @Override
    public TravelNote save(TravelNote note) {
        TravelNoteEntity entity = toEntity(note);
        if (entity.getId() == null) {
            travelNoteMapper.insert(entity);
        } else {
            travelNoteMapper.updateById(entity);
        }
        return toDomain(entity);
    }

    private TravelNote toDomain(TravelNoteEntity entity) {
        TravelNote domain = new TravelNote();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }

    private TravelNoteEntity toEntity(TravelNote note) {
        TravelNoteEntity entity = new TravelNoteEntity();
        BeanUtils.copyProperties(note, entity);
        return entity;
    }
}
