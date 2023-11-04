package com.ptfinder.ptfinderback.security;

import com.ptfinder.ptfinderback.global.error.ErrorCode;
import com.ptfinder.ptfinderback.global.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static com.ptfinder.ptfinderback.domain.user.UserRole.*;

@Slf4j
public class CustomAuthorityUtils {
    public static List<GrantedAuthority> createAuthorities(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }

    public static void verifiedRole(String role) {
        if (role == null) {
            throw new BusinessException(ErrorCode.USER_ROLE_DOES_NOT_EXISTS);
        } else if (!role.equals(USER.toString())
                && !role.equals(ADMIN.toString())
                && !role.equals(ANONYMOUS.toString())) {
            throw new BusinessException(ErrorCode.USER_ROLE_INVALID);
        }
    }
}