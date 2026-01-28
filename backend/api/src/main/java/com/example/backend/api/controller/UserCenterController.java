package com.example.backend.api.controller;

import com.example.backend.common.result.R;
import com.example.backend.service.application.UserCenterService;
import com.example.backend.service.domain.model.UserFavorite;
import com.example.backend.service.domain.model.UserLike;
import com.example.backend.service.domain.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserCenterController {

    private final UserCenterService userCenterService;

    @GetMapping("/{id}/profile")
    public R<UserProfile> profile(@PathVariable Long id) {
        return R.ok(userCenterService.profile(id));
    }

    @GetMapping("/{id}/favorites")
    public R<List<UserFavorite>> favorites(@PathVariable Long id,
                                           @RequestParam(required = false) String targetType) {
        return R.ok(userCenterService.favorites(id, targetType));
    }

    @GetMapping("/{id}/likes")
    public R<List<UserLike>> likes(@PathVariable Long id,
                                   @RequestParam(required = false) String targetType) {
        return R.ok(userCenterService.likes(id, targetType));
    }
}
