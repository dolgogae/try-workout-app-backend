package com.ptfinder.ptfinderback.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    // USER
    REGISTER_SUCCESS(201, "U001", "회원가입 되었습니다."),
    LOGIN_SUCCESS(200, "U002", "로그인 되었습니다."),
    REISSUE_SUCCESS(200, "U003", "재발급 되었습니다."),
    LOGOUT_SUCCESS(200, "U004", "로그아웃 되었습니다."),
    GET_MY_INFO_SUCCESS(200, "U005", "내 정보 조회 완료"),
    FIND_USER_SUCCESS(200, "U006", "계정 조회 완료"),

    // TRAINER
    TRAINER_CREATE_SUCCESS(201, "TR001", "트레이너 생성을 성공했습니다."),
    TRAINER_UPDATE_SUCCESS(200, "TR002", "트레이너 정보 업데이트에 성공했습니다.");

    private int status;
    private final String code;
    private final String message;
}