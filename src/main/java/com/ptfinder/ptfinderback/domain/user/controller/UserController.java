package com.ptfinder.ptfinderback.domain.user.controller;

import com.ptfinder.ptfinderback.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import com.ptfinder.ptfinderback.domain.user.UserMappingProvider;
import com.ptfinder.ptfinderback.domain.user.dto.UserDto;
import com.ptfinder.ptfinderback.domain.user.dto.UserResponseDto;
import com.ptfinder.ptfinderback.domain.user.service.UserService;
import com.ptfinder.ptfinderback.global.config.AES128Config;
import com.ptfinder.ptfinderback.global.result.ResultCode;
import com.ptfinder.ptfinderback.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMappingProvider userMappingProvider;
    private final AES128Config aes128Config;

    @GetMapping("/get")
    public ResponseEntity<ResultResponse> getUser(
            @RequestHeader String token
    ){
        String refreshToken = aes128Config.decryptAes(token.replace(" ", "+"));
        Claims claims = jwtTokenProvider.validateAndParseToken(refreshToken);
        String email = (String) claims.get("sub");


        log.info(claims.toString());
        // {sub = email 주소, iat=1694171245, exp=1694430445}
        log.info("user email {}", email);

        UserDto user = userService.getUser(email);
        UserResponseDto responseDto = userMappingProvider.userDtoToResponseDto(user);

        ResultResponse result = ResultResponse.of(ResultCode.GET_MY_INFO_SUCCESS, responseDto);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }
}
