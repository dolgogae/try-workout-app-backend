package com.tryworkout.backend.domain.member.service;

import com.tryworkout.backend.domain.member.data.Member;
import com.tryworkout.backend.domain.member.dto.MemberCreateDto;
import com.tryworkout.backend.domain.member.dto.MemberDto;
import com.tryworkout.backend.domain.member.repository.MemberJpaRepository;
import com.tryworkout.backend.domain.user.data.UserEntity;
import com.tryworkout.backend.domain.user.repository.UserJpaRepository;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceV1 implements MemberService{

    private final UserJpaRepository userJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public MemberDto createMember(MemberCreateDto memberCreateDto) {
        UserEntity userEntity = userJpaRepository.findById(memberCreateDto.getUserId()).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        Member member = Member.create(userEntity, memberCreateDto.getExercisePeriodYear());

        Member savedMember = memberJpaRepository.save(member);

        MemberDto result = MemberDto.builder()
                .id(savedMember.getId())
                .exercisePeriodYear(savedMember.getExercisePeriodYear())
                .userId(savedMember.getUser().getId())
                .build();

        return result;
    }
}
