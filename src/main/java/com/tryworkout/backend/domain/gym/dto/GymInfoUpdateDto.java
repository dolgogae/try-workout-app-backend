package com.tryworkout.backend.domain.gym.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GymInfoUpdateDto {
    @NotBlank
    private Long trainerId;
    @NotBlank
    private Long gymId;
    @NotBlank
    private String information;
}
