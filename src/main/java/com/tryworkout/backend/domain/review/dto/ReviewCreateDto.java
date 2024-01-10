package com.tryworkout.backend.domain.review.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateDto {

    private Integer score;
    private String content;
    @NotBlank
    private Long memberId;
    @NotBlank
    private Long trainerId;
}
