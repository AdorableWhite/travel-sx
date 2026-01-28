package com.example.backend.service.domain.repository;

import com.example.backend.service.domain.model.UserFavorite;
import com.example.backend.service.domain.model.UserLike;

import java.util.List;

public interface UserActionRepository {
    List<UserFavorite> listFavorites(Long userId, String targetType);

    List<UserLike> listLikes(Long userId, String targetType);
}
