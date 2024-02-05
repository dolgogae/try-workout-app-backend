package com.tryworkout.backend.domain.user.service;

import com.tryworkout.backend.domain.user.data.UserEntity;
import com.tryworkout.backend.domain.user.repository.UserJpaRepository;
import com.tryworkout.backend.domain.user.dto.UserCreateDto;
import com.tryworkout.backend.domain.user.dto.UserDto;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final UserProvider userProvider;
    private final ModelMapper mapper;

    public UserDto createUser(UserCreateDto userCreateDto) {
        if(userJpaRepository.findByEmail(userCreateDto.getEmail()).isPresent()){
            throw new BusinessException(ErrorCode.USER_EMAIL_ALREADY_EXISTS);
        }

        UserDto userDto = mapper.map(userCreateDto, UserDto.class);
        UserEntity userEntity = UserEntity.create(userDto);
        UserEntity savedUser = userJpaRepository.save(userEntity);

        UserDto result = mapper.map(savedUser, UserDto.class);
        log.info("create User = {}", result.toString());

        return result;
    }

    public UserDto getUser(String email) {

        UserEntity userEntity = userJpaRepository.findByEmail(email).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        UserDto result = mapper.map(userEntity, UserDto.class);
        log.info("get User = {}", result);

        return result;
    }

    public void deleteUser(String token) {
        String email = userProvider.getUserEmailByToken(token);
        UserEntity userEntity = userJpaRepository.findByEmail(email).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));
        userJpaRepository.deleteById(userEntity.getId());
    }

    public UserDto findUserAndUpdateTokens(Long id, String accessToken, String refreshToken) {
        UserEntity user = userJpaRepository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));
        user.updateTokens(accessToken, refreshToken);

        UserEntity savedUser = userJpaRepository.save(user);

        UserDto result = mapper.map(savedUser, UserDto.class);

        return result;
    }

    public UserDto updatePhoneNumber(String token, String phoneNumber) {
        String email = userProvider.getUserEmailByToken(token);
        UserEntity userEntity = userJpaRepository.findByEmail(email).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST)
        );

        UserEntity savedUser = userEntity.updatePhoneNumber(phoneNumber);
        UserDto result = mapper.map(savedUser, UserDto.class);
        return result;
    }

}
