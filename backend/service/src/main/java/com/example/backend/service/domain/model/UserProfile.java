package com.example.backend.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private Long id;
    private String nickname;
    private String avatar;
    private String city;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
