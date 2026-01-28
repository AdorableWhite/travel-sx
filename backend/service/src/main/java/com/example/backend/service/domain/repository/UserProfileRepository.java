package com.example.backend.service.domain.repository;

import com.example.backend.service.domain.model.UserProfile;

public interface UserProfileRepository {
    UserProfile findById(Long id);
}
