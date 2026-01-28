package com.example.backend.service.infrastructure.repository;

import com.example.backend.infrastructure.entity.UserProfileEntity;
import com.example.backend.infrastructure.mapper.UserProfileMapper;
import com.example.backend.service.domain.model.UserProfile;
import com.example.backend.service.domain.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class UserProfileRepositoryImpl implements UserProfileRepository {

    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfile findById(Long id) {
        UserProfileEntity entity = userProfileMapper.selectById(id);
        if (Objects.isNull(entity)) {
            return null;
        }
        UserProfile domain = new UserProfile();
        BeanUtils.copyProperties(entity, domain);
        return domain;
    }
}
