package com.ptfinder.ptfinderback.domain.user.service;

import com.ptfinder.ptfinderback.domain.user.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplV1Test {

    @Autowired UserService userService;

    @BeforeEach
    void init(){

    }

    @Test
    void getUserByAccountType(){
        String email = "test@gmail.com";
        String accountType = "GOOGLE";

        UserDto userDto = userService.getUserByAccountType(accountType, email);

        System.out.println(userDto);
//        assertThat(userDto.getEmail()).isEqualTo("test.mail@gmail.com");
    }
}