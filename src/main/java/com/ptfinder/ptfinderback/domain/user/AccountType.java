package com.ptfinder.ptfinderback.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {

    GOOGLE("GOOGLE", "구글 가입 회원"),
    NORMAL("NORMAL", "일반 가입 회원"),
    APPLE("APPLE", "애플 가입 회원");

    private final String key;
    private final String title;
}
