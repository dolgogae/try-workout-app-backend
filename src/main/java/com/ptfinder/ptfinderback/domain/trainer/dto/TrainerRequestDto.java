package com.ptfinder.ptfinderback.domain.trainer.dto;

import javax.validation.constraints.NotBlank;

public class TrainerRequestDto {
    @NotBlank
    private Long userId;
    @NotBlank
    private Integer fee;
    @NotBlank
    private String introduction;
    @NotBlank
    private Long gymId;
}
