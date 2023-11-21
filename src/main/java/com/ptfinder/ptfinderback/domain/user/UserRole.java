package com.ptfinder.ptfinderback.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    MEMBER("ROLE_MEMBER", "일반 회원"),
    TRAINER("ROLE_TRAINER", "미가입 사용자"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}

