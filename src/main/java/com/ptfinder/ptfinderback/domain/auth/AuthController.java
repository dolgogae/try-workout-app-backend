package com.ptfinder.ptfinderback.domain.auth;

import com.ptfinder.ptfinderback.domain.user.dto.UserDto;
import com.ptfinder.ptfinderback.domain.user.dto.UserRequestDto;
import com.ptfinder.ptfinderback.domain.user.dto.UserResponseDto;
import com.ptfinder.ptfinderback.domain.user.service.UserService;
import com.ptfinder.ptfinderback.global.result.ResultCode;
import com.ptfinder.ptfinderback.global.result.ResultResponse;
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

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Operation(summary = "회원가입(sign-in)", description = "회원가입을 위한 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(schema = @Schema(implementation = UserRequestDto.class)))
    })
    @PostMapping("/sign-up")
    public ResponseEntity<ResultResponse> signIn(
            @RequestBody UserRequestDto userRequestDto
    ){
        log.info(userRequestDto.toString());

        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        UserDto userDto = mapper.map(userRequestDto, UserDto.class);
        UserDto user = userService.createUser(userDto);

        log.info("create user = {}", user.toString());

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

        ResultResponse result = ResultResponse.of(ResultCode.REGISTER_SUCCESS, tokenDto);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }

}
