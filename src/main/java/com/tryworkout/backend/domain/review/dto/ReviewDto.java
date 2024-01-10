package com.tryworkout.backend.domain.review.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private Integer score;
    private String content;
    private Long memberId;
    private Long trainerId;
}
