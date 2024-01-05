package com.tryworkout.backend.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    INTERNAL_SERVER_ERROR(500, "CE001", "internal server error"),
    INVALID_INPUT_VALUE(400, "CE002", "invalid input type"),
    METHOD_NOT_ALLOWED(405, "CE003", "method not allowed"),
    INVALID_TYPE_VALUE(400, "CE004", "invalid type value"),
    BAD_CREDENTIALS(400, "CE005", "bad credentials"),
    TOKEN_NOT_EXIST(401, "CE006", "token is null"),
    UNAUTHORIZED(401, "CE006", "unauthorized"),

    // User
    USER_NOT_EXIST(404, "ME001", "member not exist"),
    USER_EMAIL_ALREADY_EXISTS(400, "ME002", "user email already exists"),
    NO_AUTHORITY(403, "ME003", "no authority"),
    NEED_LOGIN(401, "ME004", "need login"),
    AUTHENTICATION_NOT_FOUND(401, "ME005", "Security Context에 인증 정보가 없습니다."),
    USER_ALREADY_LOGOUT(400, "ME006", "member already logout"),
    USER_ROLE_DOES_NOT_EXISTS(404, "ME007", "member role does not exists"),
    USER_ROLE_INVALID(404, "ME008", "member role invalid"),

    // Auth
    REFRESH_TOKEN_INVALID(400, "AE001", "refresh token invalid"),
    NO_ACCESS_TOKEN(404, "AE002", "no access token"),
    TOKEN_EXPIRED(404, "AE003", "token expired"),
    TOKEN_UNSUPPORTED(404, "AE004", "token unsupported"),
    TOKEN_ILLEGAL_ARGUMENT(404, "AE004", "token illegal argument"),

    // Encrypt
    ENCRYPTION_FAILED(400, "EE001", "Encryption failed"),
    DECRYPTION_FAILED(400, "EE002", "Decryption failed"),

    // GYM
    GYM_NOT_FOUND(400, "GE001", "Gym is not exist"),

    // FEE
    FEE_NOT_FOUND(400, "FE001", "FEE is not exist"),

    // IMAGE
    IMAGE_TYPE_NOT_MATCHING(400, "IE001", "Image type is not matching"),
    IMAGE_EMPTY(400, "IE002", "Failed to store empty file"),
    IMAGE_NOT_STORE(400, "IE003", "Cannot store file with relative path outside current directory");

    private int status;
    private final String code;
    private final String message;
}