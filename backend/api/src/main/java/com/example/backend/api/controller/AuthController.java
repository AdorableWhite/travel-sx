package com.example.backend.api.controller;

import com.example.backend.common.result.R;
import com.example.backend.service.AuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @GetMapping("/captcha")
    public R<Map<String, String>> getCaptcha() {
        return R.ok(authService.getCaptcha());
    }

    @PostMapping("/send-sms")
    public R<Void> sendSms(@RequestBody Map<String, String> params) {
        String phone = params.get("phone");
        authService.sendSmsCode(phone);
        return R.ok();
    }

    @PostMapping("/register")
    public R<Void> register(@RequestBody RegisterRequest req) {
        return authService.register(req.getPhone(), req.getCode(), req.getPassword());
    }

    @PostMapping("/login/password")
    public R<String> loginByPassword(@RequestBody LoginPasswordRequest req) {
        return authService.loginByPassword(req.getAccount(), req.getPassword(), req.getCaptchaKey(), req.getCaptchaCode());
    }

    @PostMapping("/login/sms")
    public R<String> loginBySms(@RequestBody LoginSmsRequest req) {
        return authService.loginBySms(req.getPhone(), req.getCode());
    }

    @Data
    public static class RegisterRequest {
        private String phone;
        private String code;
        private String password;
    }

    @Data
    public static class LoginPasswordRequest {
        private String account;
        private String password;
        private String captchaKey;
        private String captchaCode;
    }

    @Data
    public static class LoginSmsRequest {
        private String phone;
        private String code;
    }
}
