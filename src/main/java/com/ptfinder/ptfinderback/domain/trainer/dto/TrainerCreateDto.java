package com.ptfinder.ptfinderback.domain.trainer.dto;

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
    private Integer fee;
    @NotBlank
    private String introduction;
    @NotBlank
    private Long gymId;
}
