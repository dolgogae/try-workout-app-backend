package com.ptfinder.ptfinderback.global.error.exception;

import com.ptfinder.ptfinderback.global.error.ErrorCode;

public class AuthenticationNotFoundException extends BusinessException {
    public AuthenticationNotFoundException() {
        super(ErrorCode.AUTHENTICATION_NOT_FOUND);
    }
}