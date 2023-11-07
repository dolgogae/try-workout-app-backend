package com.ptfinder.ptfinderback.domain.user.service;

import com.ptfinder.ptfinderback.domain.user.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    UserDto getUser(String email);
    UserDto deleteUser(UserDto userDto);
    UserDto findUserAndUpdateTokens(Long id, String accessToken, String refreshToken);
    UserDto getUserByAccountType(String accountType, String email);
}
