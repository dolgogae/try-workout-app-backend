package com.tryworkout.backend.domain.user.controller;

import com.tryworkout.backend.domain.user.dto.UserDto;
import com.tryworkout.backend.domain.user.dto.UserResponseDto;
import com.tryworkout.backend.domain.user.service.UserProvider;
import com.tryworkout.backend.domain.user.service.UserService;
import com.tryworkout.backend.global.response.ResultCode;
import com.tryworkout.backend.global.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<ResultResponse> getUser(
            @RequestHeader String token
    ){
        String email = userProvider.getUserEmailByToken(token);
        // {sub = email 주소, iat=1694171245, exp=1694430445}
        log.info("userDto email {}", email);

        UserDto userDto = userService.getUser(email);
        UserResponseDto responseDto = mapper.map(userDto, UserResponseDto.class);

        ResultResponse result = ResultResponse.of(ResultCode.GET_MY_INFO_SUCCESS, responseDto);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }

    @PutMapping("phone-number")
    public ResponseEntity<ResultResponse> updatePhoneNumber(
        @RequestHeader String token,
        @RequestBody String phoneNumber
    ){
        UserDto userDto = userService.updatePhoneNumber(token, phoneNumber);
        UserResponseDto responseDto = mapper.map(userDto, UserResponseDto.class);

        ResultResponse result = ResultResponse.of(ResultCode.UPDATE_SUCCESS, responseDto);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }

    @DeleteMapping
    public ResponseEntity<ResultResponse> deleteUser(
            @RequestHeader String token
    ){
        userService.deleteUser(token);

        ResultResponse result = ResultResponse.of(ResultCode.DELETE_SUCCESS, "delete success");
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }
}
