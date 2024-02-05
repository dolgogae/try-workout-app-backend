package com.tryworkout.backend.unit.domain.member.service;

import com.tryworkout.backend.domain.member.data.Member;
import com.tryworkout.backend.domain.member.dto.MemberCreateDto;
import com.tryworkout.backend.domain.member.dto.MemberDto;
import com.tryworkout.backend.domain.member.repository.MemberJpaRepository;
import com.tryworkout.backend.domain.member.service.MemberService;
import com.tryworkout.backend.domain.user.enums.AccountType;
import com.tryworkout.backend.domain.user.enums.UserRole;
import com.tryworkout.backend.domain.user.data.UserEntity;
import com.tryworkout.backend.domain.user.dto.UserDto;
import com.tryworkout.backend.domain.user.repository.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@SpringBootTest
class MemberServiceTest {

    @Mock
    UserJpaRepository userJpaRepository;
    @Mock
    MemberJpaRepository memberJpaRepository;

    MemberService memberService;
    @BeforeEach
    void before(){
        memberService = new MemberService(userJpaRepository, memberJpaRepository);
    }

    @Test
    void creatMemberTest(){
        // given
        MemberCreateDto memberCreateDto = MemberCreateDto.builder()
                .userId(1L)
                .exercisePeriodYear(4)
                .build();
        UserDto userDto = UserDto.builder()
                .id(1L)
                .email("test@domain.com")
                .username("user")
                .password("1234")
                .userRole(String.valueOf(UserRole.MEMBER))
                .accountType(String.valueOf(AccountType.NORMAL))
                .build();
        UserEntity userEntity = UserEntity.create(userDto);
        setField(userEntity, "id", 1L);
        Member member = Member.create(userEntity, 4);
        setField(member, "id", 1L);

        when(userJpaRepository.findById(1L)).thenReturn(Optional.ofNullable(userEntity));
        when(memberJpaRepository.save(any())).thenReturn(member);

        // when
        MemberDto result = memberService.createMember(memberCreateDto);

        // then
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getUserId());
        assertEquals(4, result.getExercisePeriodYear());
    }
}