package com.ptfinder.ptfinderback.domain.user.service;

import com.ptfinder.ptfinderback.domain.user.AccountType;
import com.ptfinder.ptfinderback.domain.user.UserMappingProvider;
import com.ptfinder.ptfinderback.domain.user.UserRole;
import com.ptfinder.ptfinderback.domain.user.data.UserEntity;
import com.ptfinder.ptfinderback.domain.user.dto.UserDto;
import com.ptfinder.ptfinderback.domain.user.repository.UserJpaRepository;
import com.ptfinder.ptfinderback.global.error.ErrorCode;
import com.ptfinder.ptfinderback.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplV1 implements UserService{

    private final UserJpaRepository userJpaRepository;
    private final UserMappingProvider userMappingProvider;

    @Override
    public UserDto createUser(UserDto userDto) {
//        userDto.setRole(UserRole.TRAINER.name());
        log.info(userDto.toString());
        UserEntity userEntity = UserEntity.create(userDto);
        UserEntity savedUser = userJpaRepository.save(userEntity);

        UserDto result = userMappingProvider.userEntityToUserDto(savedUser);
        log.info(result.toString());

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

        UserDto result = userMappingProvider.userEntityToUserDto(userEntity);
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

        UserDto userDto = userMappingProvider.userEntityToUserDto(savedUser);

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

        UserDto userDto = userMappingProvider.userEntityToUserDto(findUser);

        return userDto;
    }
}
