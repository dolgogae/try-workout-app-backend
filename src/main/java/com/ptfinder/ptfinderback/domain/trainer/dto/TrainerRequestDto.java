package com.ptfinder.ptfinderback.domain.trainer.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
