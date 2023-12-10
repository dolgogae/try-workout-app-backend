package com.ptfinder.ptfinderback.domain.user.service;

import com.ptfinder.ptfinderback.domain.user.dto.UserCreateDto;
import com.ptfinder.ptfinderback.domain.user.dto.UserDto;

public interface UserService {

    UserDto createUser(UserCreateDto userCreateDto);
    UserDto getUser(String email);
    void deleteUser(String token);
    UserDto findUserAndUpdateTokens(Long id, String accessToken, String refreshToken);
    UserDto updatePhoneNumber(String token, String phoneNumber);
}
