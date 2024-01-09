package com.tryworkout.backend.domain.member.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateDto {

    private Long userId;
    private Integer exercisePeriodYear;
}
