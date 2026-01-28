package com.example.backend.service.application;

import com.example.backend.service.domain.model.UserFavorite;
import com.example.backend.service.domain.model.UserLike;
import com.example.backend.service.domain.model.UserProfile;
import com.example.backend.service.domain.repository.UserActionRepository;
import com.example.backend.service.domain.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCenterService {

    private final UserProfileRepository userProfileRepository;
    private final UserActionRepository userActionRepository;

    public UserProfile profile(Long userId) {
        return userProfileRepository.findById(userId);
    }

    public List<UserFavorite> favorites(Long userId, String targetType) {
        return userActionRepository.listFavorites(userId, targetType);
    }

    public List<UserLike> likes(Long userId, String targetType) {
        return userActionRepository.listLikes(userId, targetType);
    }
}
