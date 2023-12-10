package com.ptfinder.ptfinderback.domain.user.service;

import com.ptfinder.ptfinderback.domain.user.data.UserEntity;
import com.ptfinder.ptfinderback.domain.user.repository.UserJpaRepository;
import com.ptfinder.ptfinderback.global.config.AES128Config;
import com.ptfinder.ptfinderback.global.error.ErrorCode;
import com.ptfinder.ptfinderback.global.error.exception.BusinessException;
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
    private final UserJpaRepository userJpaRepository;

    public String getUserEmailByToken(String token){
        String refreshToken = aes128Config.decryptAes(token);
        Claims claims = jwtTokenProvider.validateAndParseToken(refreshToken);
        return (String) claims.get("sub");
    }

    public void checkTrainerRole(String token){
        String email = getUserEmailByToken(token);

        UserEntity userEntity = userJpaRepository.findByEmail(email).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        if(!userEntity.getUserRole().name().equals("TRAINER")){
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
    }

}
