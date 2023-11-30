package com.ptfinder.ptfinderback.domain.user.controller;

import com.ptfinder.ptfinderback.domain.user.dto.UserDto;
import com.ptfinder.ptfinderback.domain.user.dto.UserResponseDto;
import com.ptfinder.ptfinderback.domain.user.service.UserService;
import com.ptfinder.ptfinderback.global.config.AES128Config;
import com.ptfinder.ptfinderback.global.result.ResultCode;
import com.ptfinder.ptfinderback.global.result.ResultResponse;
import com.ptfinder.ptfinderback.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final AES128Config aes128Config;
    private final ModelMapper mapper;

    @GetMapping("")
    public ResponseEntity<ResultResponse> getUser(
            @RequestHeader String token
    ){
        String refreshToken = aes128Config.decryptAes(token);
        Claims claims = jwtTokenProvider.validateAndParseToken(refreshToken);
        String email = (String) claims.get("sub");
        // {sub = email 주소, iat=1694171245, exp=1694430445}
        log.info("userDto email {}", email);

        UserDto userDto = userService.getUser(email);
        UserResponseDto responseDto = mapper.map(userDto, UserResponseDto.class);

        ResultResponse result = ResultResponse.of(ResultCode.GET_MY_INFO_SUCCESS, responseDto);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }
}
