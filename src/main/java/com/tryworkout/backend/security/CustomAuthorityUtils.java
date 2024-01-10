package com.tryworkout.backend.security;

import com.tryworkout.backend.domain.user.enums.UserRole;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Slf4j
public class CustomAuthorityUtils {
    public static List<GrantedAuthority> createAuthorities(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }

    public static void verifiedRole(String role) {
        if (role == null) {
            throw new BusinessException(ErrorCode.USER_ROLE_DOES_NOT_EXISTS);
        } else if (!role.equals(UserRole.MEMBER.toString())
                && !role.equals(UserRole.ADMIN.toString())
                && !role.equals(UserRole.TRAINER.toString())) {
            throw new BusinessException(ErrorCode.USER_ROLE_INVALID);
        }
    }
}