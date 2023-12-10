package com.ptfinder.ptfinderback.domain.user.service;

import com.ptfinder.ptfinderback.domain.user.data.UserEntity;
import com.ptfinder.ptfinderback.domain.user.dto.UserCreateDto;
import com.ptfinder.ptfinderback.domain.user.dto.UserDto;
import com.ptfinder.ptfinderback.domain.user.repository.UserJpaRepository;
import com.ptfinder.ptfinderback.global.error.ErrorCode;
import com.ptfinder.ptfinderback.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplV1 implements UserService{

    private final UserJpaRepository userJpaRepository;
    private final UserProvider userProvider;
    private final ModelMapper mapper;

    @Override
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

    @Override
    public UserDto getUser(String email) {

        UserEntity userEntity = userJpaRepository.findByEmail(email).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        UserDto result = mapper.map(userEntity, UserDto.class);
        log.info("get User = {}", result);

        return result;
    }

    @Override
    public void deleteUser(String token) {
        String email = userProvider.getUserEmailByToken(token);
        UserEntity userEntity = userJpaRepository.findByEmail(email).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));
        userJpaRepository.deleteById(userEntity.getId());
    }

    @Override
    public UserDto findUserAndUpdateTokens(Long id, String accessToken, String refreshToken) {
        UserEntity user = userJpaRepository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));
        user.updateTokens(accessToken, refreshToken);

        UserEntity savedUser = userJpaRepository.save(user);

        UserDto result = mapper.map(savedUser, UserDto.class);

        return result;
    }

    @Override
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
