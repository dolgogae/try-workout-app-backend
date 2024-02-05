package com.tryworkout.backend.global.security;

import com.tryworkout.backend.domain.user.data.UserEntity;
import com.tryworkout.backend.domain.user.repository.UserJpaRepository;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("email = {}", email);
        UserDetails userDetails = userJpaRepository.findByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXIST));
        log.info(userDetails.toString());
        return userDetails;
    }

    private UserDetails createUserDetails(UserEntity user) {
        return CustomUserDetails.of(user);
    }
}