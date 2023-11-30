package com.ptfinder.ptfinderback.domain.gym.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GymLocationDto {

    private Float mapX;
    private Float mapY;
}
