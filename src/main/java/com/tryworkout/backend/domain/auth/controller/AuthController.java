package com.tryworkout.backend.domain.auth.controller;

import com.tryworkout.backend.domain.auth.dto.TokenDto;
import com.tryworkout.backend.domain.auth.service.AuthService;
import com.tryworkout.backend.domain.user.dto.UserCreateDto;
import com.tryworkout.backend.domain.user.dto.UserDto;
import com.tryworkout.backend.domain.user.dto.UserResponseDto;
import com.tryworkout.backend.domain.user.service.UserService;
import com.tryworkout.backend.global.result.ResultCode;
import com.tryworkout.backend.global.result.ResultResponse;
import com.tryworkout.backend.security.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper mapper;
    private final AuthService authService;

    @Operation(summary = "회원가입(sign-in)", description = "회원가입을 위한 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(schema = @Schema(implementation = UserCreateDto.class)))
    })
    @PostMapping("/sign-up")
    public ResponseEntity<ResultResponse> signUp(
            @RequestBody UserCreateDto userCreateDto
    ){
        log.info(userCreateDto.toString());

        userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        UserDto user = userService.createUser(userCreateDto);

        UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);

        ResultResponse result = ResultResponse.of(ResultCode.REGISTER_SUCCESS, responseDto);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }

    @Operation(summary = "JWT 로그인 성공 콜백 함수", description = "JWT 로그인 이후 성공 콜백 함수")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(schema = @Schema(implementation = TokenDto.class)))
    })
    @GetMapping("/login/callback")
    public ResponseEntity<ResultResponse> loginCallback(
            @RequestParam String accessToken, @RequestParam String refreshToken
    ){
        TokenDto tokenDto = TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        log.info(tokenDto.toString());

        ResultResponse result = ResultResponse.of(ResultCode.LOGIN_SUCCESS, tokenDto);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }

    @PatchMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        String encryptedRefreshToken = jwtTokenProvider.resolveRefreshToken(request);
        String accessToken = jwtTokenProvider.resolveAccessToken(request);
        authService.logout(encryptedRefreshToken, accessToken);


        ResultResponse result = ResultResponse.of(ResultCode.LOGOUT_SUCCESS, "logout success!");
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }


}
