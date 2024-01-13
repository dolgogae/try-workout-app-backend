package com.tryworkout.backend.domain.member.service;

import com.tryworkout.backend.domain.member.dto.MemberCreateDto;
import com.tryworkout.backend.domain.member.dto.MemberDto;

public interface MemberService {

    MemberDto createMember(MemberCreateDto memberCreateDto);
    MemberDto deleteMember(Long memberId);
}
