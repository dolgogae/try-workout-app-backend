package com.tryworkout.backend.domain.auth.service;

public interface AuthService {
    void logout(String encryptedRefreshToken, String accessToken);
}
