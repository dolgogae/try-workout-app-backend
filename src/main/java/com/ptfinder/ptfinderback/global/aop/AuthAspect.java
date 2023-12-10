package com.ptfinder.ptfinderback.global.aop;

import com.ptfinder.ptfinderback.domain.user.data.UserEntity;
import com.ptfinder.ptfinderback.domain.user.repository.UserJpaRepository;
import com.ptfinder.ptfinderback.domain.user.service.UserProvider;
import com.ptfinder.ptfinderback.global.error.ErrorCode;
import com.ptfinder.ptfinderback.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class AuthAspect {

    private final UserProvider userProvider;
    private final UserJpaRepository userJpaRepository;

    @Before("@annotation(com.ptfinder.ptfinderback.global.aop.AuthCheck) && args(token)")
    public void beforeAuthCheckExecution(String token){

        String email = userProvider.getUserEmailByToken(token);

        UserEntity userEntity = userJpaRepository.findByEmail(email).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        if(!userEntity.getUserRole().name().equals("TRAINER")){
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
    }
}
