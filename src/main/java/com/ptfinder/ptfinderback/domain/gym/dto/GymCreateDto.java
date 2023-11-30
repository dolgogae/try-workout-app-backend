package com.ptfinder.ptfinderback.domain.gym.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GymCreateDto {

    @NotBlank
    private String gymName;
    private String address;
    private String roadAddress;
    @NotBlank
    private Float mapX;
    @NotBlank
    private Float mapY;
}
