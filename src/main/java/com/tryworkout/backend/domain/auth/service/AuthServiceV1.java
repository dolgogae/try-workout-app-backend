package com.tryworkout.backend.domain.auth.service;

import com.tryworkout.backend.domain.user.data.UserEntity;
import com.tryworkout.backend.domain.user.repository.UserJpaRepository;
import com.tryworkout.backend.global.config.AES128Config;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import com.tryworkout.backend.redis.RedisService;
import com.tryworkout.backend.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceV1 implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AES128Config aes128Config;
    private final RedisService redisService;
    private final UserJpaRepository userJpaRepository;

    @Override
    public void logout(String encryptedRefreshToken, String accessToken) {
        this.verifiedRefreshToken(encryptedRefreshToken);
        String refreshToken = aes128Config.decryptAes(encryptedRefreshToken);
        Claims claims = jwtTokenProvider.parseClaims(refreshToken);
        String email = claims.getSubject();
        String redisRefreshToken = redisService.getValues(email);
        if (redisService.checkExistsValue(redisRefreshToken)) {
            redisService.deleteValues(email);

            // 로그아웃 시 Access Token Redis 저장 ( key = Access Token / value = "logout" )
            long accessTokenExpirationMillis = jwtTokenProvider.getAccessTokenExpirationMillis();
            redisService.setValues(accessToken, "logout", Duration.ofMillis(accessTokenExpirationMillis));
        }
    }

    private void verifiedRefreshToken(String encryptedRefreshToken) {
        if (encryptedRefreshToken == null) {
            throw new BusinessException(ErrorCode.TOKEN_UNSUPPORTED);
        }
    }

    private UserEntity findMemberByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXIST));
    }
}
