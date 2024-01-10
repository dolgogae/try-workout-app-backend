package com.tryworkout.backend.global.result;

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
    DELETE_SUCCESS(200, "U007", "계정 삭제 완료"),
    UPDATE_SUCCESS(200, "U008", "계정 업데이트 완료"),

    // TRAINER
    TRAINER_CREATE_SUCCESS(201, "TR001", "트레이너 생성을 성공했습니다."),
    TRAINER_UPDATE_SUCCESS(200, "TR002", "트레이너 정보 업데이트에 성공했습니다."),
    TRAINER_FEE_CREATED(200, "TR003", "트레이너 요금 정보를 업데이트 했습니다"),

    // MEMBER
    MEMBER_CREATE_SUCCESS(201, "M001", "멤버 생성을 성공했습니다."),

    // GYM
    GET_GYM_SUCCESS(200, "G001", "체육관을 가져오는데 성공했습니다."),
    GET_NEAREST_GYM_SUCCESS(200, "G002", "가까운 체육관 갯수를 가져오는데 성공했습니다."),

    // FEE
    FEE_CREATE_SUCCESS(201, "F001", "요금 정보 생성을 성공했습니다." ),
    FEE_UPDATE_SUCCESS(200, "F002", "요금 정보 업데이트를 성공했습니다." ),
    FEE_DELETE_SUCCESS(200, "F003", "요금 삭제를 성공했습니다."),

    // RESERVATION
    RESERVATION_REGISTER_SUCCESS(201, "R001", "예약을 성공했습니다."),
    RESERVATION_DELETE_SUCCESS(200, "R002", "예약을 삭제했습니다."),

    // REVIEW
    REVIEW_CREATE_SUCCESS(201, "RV001", "리뷰 등록에 성공했습니다."),
    REVIEW_DELETE_SUCCESS(200, "RV002", "리뷰 삭제에 성공했습니다."),

    // IMAGE
    IMAGE_UPLOAD_SUCCESS(200, "I001", "이미지 업로드를 성공했습니다.");

    private int status;
    private final String code;
    private final String message;
}