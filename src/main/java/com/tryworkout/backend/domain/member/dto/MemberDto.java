package com.tryworkout.backend.domain.member.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private Long userId;
    private Integer exercisePeriodYear;
}
