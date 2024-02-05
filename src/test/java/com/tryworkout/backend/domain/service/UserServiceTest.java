package com.tryworkout.backend.domain.service;

import com.tryworkout.backend.domain.user.data.UserEntity;
import com.tryworkout.backend.domain.user.dto.UserCreateDto;
import com.tryworkout.backend.domain.user.dto.UserDto;
import com.tryworkout.backend.domain.user.repository.UserJpaRepository;
import com.tryworkout.backend.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserJpaRepository userJpaRepository;

    UserEntity user;
    @BeforeEach
    void init(){

        Optional<UserEntity> optUser = userJpaRepository.findByEmail("test.member@domain.com");
        optUser.ifPresent(userEntity -> user = userEntity);
    }

    @Test
    @DisplayName("유저 생성 테스트")
    public void createUserTest(){
        // given
        UserCreateDto dto = UserCreateDto.builder()
                .username("테스트 용")
                .email("test@test.com")
                .password("123456")
                .userRole("MEMBER")
                .accountType("NORMAL").build();

        // when
        UserDto userDto = userService.createUser(dto);

        // then
        assertThat(userDto.getUsername()).isEqualTo("테스트 용");
        assertThat(userDto.getEmail()).isEqualTo("test@test.com");
        assertThat(userDto.getUserRole()).isEqualTo("MEMBER");
    }

    @Test
    @DisplayName("유저 조회 테스트")
    public void getUserTest(){
        // given

        // when
        UserDto userDto = userService.getUser(user.getEmail());

        // then
        assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
        assertThat(userDto.getUsername()).isEqualTo(user.getUsername());
        assertThat(userDto.getUserRole()).isEqualTo(user.getUserRole().name());
    }
}