package com.tryworkout.backend.domain.trainer.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainerCreateDto {
    @NotBlank
    private Long userId;
    @NotBlank
    private String introduction;
    private Long gymId;
    private String trainerType;
}
