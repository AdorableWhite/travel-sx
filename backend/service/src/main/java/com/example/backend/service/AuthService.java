package com.example.backend.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.common.result.R;
import com.example.backend.infrastructure.entity.SysUser;
import com.example.backend.infrastructure.mapper.SysUserMapper;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final SysUserMapper userMapper;
    private final StringRedisTemplate redisTemplate;

    private static final String CAPTCHA_KEY_PREFIX = "captcha:";
    private static final String SMS_CODE_KEY_PREFIX = "sms:";

    // 1. Generate Graphic Captcha
    public Map<String, String> getCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        
        String key = IdUtil.simpleUUID();
        String code = specCaptcha.text().toLowerCase();
        
        // Save to Redis (expire in 5 mins)
        redisTemplate.opsForValue().set(CAPTCHA_KEY_PREFIX + key, code, 5, TimeUnit.MINUTES);
        
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("image", specCaptcha.toBase64());
        return map;
    }

    // 2. Send SMS Code (Mock)
    public void sendSmsCode(String phone) {
        // Check if user exists (optional, depending on requirement. Here we allow new users)
        
        String code = RandomUtil.randomNumbers(6);
        // Mock sending: just log it
        log.info(">>> Sending SMS to {}: {}", phone, code);
        
        // Save to Redis (expire in 5 mins)
        redisTemplate.opsForValue().set(SMS_CODE_KEY_PREFIX + phone, code, 5, TimeUnit.MINUTES);
    }

    // 3. Register (Phone + Code + Password)
    public R<Void> register(String phone, String code, String password) {
        String cacheCode = redisTemplate.opsForValue().get(SMS_CODE_KEY_PREFIX + phone);
        if (StrUtil.isBlank(cacheCode) || !cacheCode.equals(code)) {
            return R.fail("Invalid or expired SMS code");
        }
        
        // Check phone uniqueness
        Long count = userMapper.selectCount(new LambdaQueryWrapper<SysUser>().eq(SysUser::getPhone, phone));
        if (count > 0) {
            return R.fail("Phone number already registered");
        }

        SysUser user = new SysUser();
        user.setPhone(phone);
        // Using phone as default username initially
        user.setUsername(phone); 
        user.setSalt(RandomUtil.randomString(6));
        user.setPassword(SaSecureUtil.md5BySalt(password, user.getSalt()));
        user.setCreateTime(LocalDateTime.now());
        
        userMapper.insert(user);
        
        // Clear code
        redisTemplate.delete(SMS_CODE_KEY_PREFIX + phone);
        return R.ok();
    }

    // 4. Login (Username/Phone + Password + Captcha)
    public R<String> loginByPassword(String account, String password, String captchaKey, String captchaCode) {
        // Verify Captcha
        String cacheCaptcha = redisTemplate.opsForValue().get(CAPTCHA_KEY_PREFIX + captchaKey);
        if (StrUtil.isBlank(cacheCaptcha) || !cacheCaptcha.equalsIgnoreCase(captchaCode)) {
            redisTemplate.delete(CAPTCHA_KEY_PREFIX + captchaKey); // delete once used
            return R.fail("Invalid Captcha");
        }
        redisTemplate.delete(CAPTCHA_KEY_PREFIX + captchaKey);

        // Find User
        SysUser user = userMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, account)
                .or()
                .eq(SysUser::getPhone, account));
                
        if (user == null) {
            return R.fail("Account or password error");
        }
        
        String inputMd5 = SaSecureUtil.md5BySalt(password, user.getSalt());
        if (!inputMd5.equals(user.getPassword())) {
            return R.fail("Account or password error");
        }
        
        // Login
        StpUtil.login(user.getId());
        return R.ok(StpUtil.getTokenValue());
    }

    // 5. Login (Phone + SMS Code)
    public R<String> loginBySms(String phone, String code) {
         String cacheCode = redisTemplate.opsForValue().get(SMS_CODE_KEY_PREFIX + phone);
        if (StrUtil.isBlank(cacheCode) || !cacheCode.equals(code)) {
            return R.fail("Invalid or expired SMS code");
        }
        
        SysUser user = userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getPhone, phone));
        if (user == null) {
            return R.fail("User not found, please register first");
        }
        
        redisTemplate.delete(SMS_CODE_KEY_PREFIX + phone);
        StpUtil.login(user.getId());
        return R.ok(StpUtil.getTokenValue());
    }
}
