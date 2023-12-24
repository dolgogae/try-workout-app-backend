package com.tryworkout.backend.domain.user.service;

import com.tryworkout.backend.domain.user.dto.UserCreateDto;
import com.tryworkout.backend.domain.user.dto.UserDto;

public interface UserService {

    UserDto createUser(UserCreateDto userCreateDto);
    UserDto getUser(String email);
    void deleteUser(String token);
    UserDto findUserAndUpdateTokens(Long id, String accessToken, String refreshToken);
    UserDto updatePhoneNumber(String token, String phoneNumber);
}
