package com.ptfinder.ptfinderback.domain.gym.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GymDto {

    private Long id;
    private String gymName;
    private String address;
    private String roadAddress;
    private String information;
    private Boolean parkingYn;
    private Integer parkingFee;
    private Boolean wearYn;
    private Integer wearFee;
    private Boolean lockerYn;
    private Integer lockerFee;
    private String businessHoursWeek;
    private String businessHoursWeekend;
    private String holiday;
    private Float mapX;
    private Float mapY;
}
