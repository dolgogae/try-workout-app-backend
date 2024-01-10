package com.tryworkout.backend.domain.gym.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GymFeeUpdateDto {
    @NotBlank
    private Long trainerId;
    @NotBlank
    private Long gymId;
    private Boolean parkingYn;
    private Integer parkingFee;
    private Boolean wearYn;
    private Integer wearFee;
    private Boolean lockerYn;
    private Integer lockerFee;
}
