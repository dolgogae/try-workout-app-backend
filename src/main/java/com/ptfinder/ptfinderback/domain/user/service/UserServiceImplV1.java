package com.ptfinder.ptfinderback.domain.user.service;

import com.ptfinder.ptfinderback.domain.user.AccountType;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplV1 implements UserService{

    private final UserJpaRepository userJpaRepository;
    private final ModelMapper mapper;

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        UserDto userDto = mapper.map(userCreateDto, UserDto.class);
        UserEntity userEntity = UserEntity.create(userDto);
        UserEntity savedUser = userJpaRepository.save(userEntity);

        UserDto result = mapper.map(savedUser, UserDto.class);
        log.info("create User = {}", result.toString());

        return result;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
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
    public UserDto deleteUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto findUserAndUpdateTokens(Long id, String accessToken, String refreshToken) {
        UserEntity user = userJpaRepository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));
        user.setTokens(accessToken, refreshToken);

        UserEntity savedUser = userJpaRepository.save(user);

        UserDto userDto = mapper.map(savedUser, UserDto.class);

        return userDto;
    }

    @Override
    public UserDto getUserByAccountType(String accountType, String email) {

        AccountType type = AccountType.valueOf(accountType);

        UserEntity findUser;
        Optional<UserEntity> optionalUser = userJpaRepository.findByEmailAndAccountType(email, type);
        if(optionalUser.isEmpty()){
            return null;
        } else {
            findUser = optionalUser.get();
        }

        UserDto userDto = mapper.map(findUser, UserDto.class);

        return userDto;
    }
}
