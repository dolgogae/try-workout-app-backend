package com.ptfinder.ptfinderback.domain.user.service;

import com.ptfinder.ptfinderback.global.config.AES128Config;
import com.ptfinder.ptfinderback.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service @Slf4j
@RequiredArgsConstructor
public class UserProvider {

    private final AES128Config aes128Config;
    private final JwtTokenProvider jwtTokenProvider;

    public String getUserByToken(String token){
        String refreshToken = aes128Config.decryptAes(token);
        log.info("refresh Token = {}", refreshToken);
        Claims claims = jwtTokenProvider.validateAndParseToken(refreshToken);
        return (String) claims.get("sub");
    }
}
