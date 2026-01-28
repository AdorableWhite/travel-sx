package com.example.backend.service.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.infrastructure.entity.UserFavoriteEntity;
import com.example.backend.infrastructure.entity.UserLikeEntity;
import com.example.backend.infrastructure.mapper.UserFavoriteMapper;
import com.example.backend.infrastructure.mapper.UserLikeMapper;
import com.example.backend.service.domain.model.UserFavorite;
import com.example.backend.service.domain.model.UserLike;
import com.example.backend.service.domain.repository.UserActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserActionRepositoryImpl implements UserActionRepository {

    private final UserFavoriteMapper userFavoriteMapper;
    private final UserLikeMapper userLikeMapper;

    @Override
    public List<UserFavorite> listFavorites(Long userId, String targetType) {
        QueryWrapper<UserFavoriteEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        if (targetType != null && !targetType.isBlank()) {
            wrapper.eq("target_type", targetType);
        }
        return userFavoriteMapper.selectList(wrapper).stream()
                .map(this::toFavoriteDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserLike> listLikes(Long userId, String targetType) {
        QueryWrapper<UserLikeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        if (targetType != null && !targetType.isBlank()) {
            wrapper.eq("target_type", targetType);
        }
        return userLikeMapper.selectList(wrapper).stream()
                .map(this::toLikeDomain)
                .collect(Collectors.toList());
    }

    private UserFavorite toFavoriteDomain(UserFavoriteEntity entity) {
        UserFavorite domain = new UserFavorite();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }

    private UserLike toLikeDomain(UserLikeEntity entity) {
        UserLike domain = new UserLike();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }
}
