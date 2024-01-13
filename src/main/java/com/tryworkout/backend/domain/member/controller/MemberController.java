package com.tryworkout.backend.domain.member.controller;

import com.tryworkout.backend.domain.member.dto.MemberCreateDto;
import com.tryworkout.backend.domain.member.dto.MemberDto;
import com.tryworkout.backend.domain.member.service.MemberService;
import com.tryworkout.backend.global.result.ResultCode;
import com.tryworkout.backend.global.result.ResultResponse;
import com.tryworkout.backend.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<ResultResponse> createMember(
            @RequestBody MemberCreateDto memberCreateDto
    ){
        MemberDto memberDto = memberService.createMember(memberCreateDto);

        ResultResponse result = ResultResponse.of(ResultCode.MEMBER_CREATE_SUCCESS, memberDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<ResultResponse> deleteMember(
            @RequestHeader String token,
            @PathVariable Long memberId
    ){
        jwtTokenProvider.validateAndParseToken(token);

        MemberDto memberDto = memberService.deleteMember(memberId);
        ResultResponse result = ResultResponse.of(ResultCode.MEMBER_CREATE_SUCCESS, memberDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
