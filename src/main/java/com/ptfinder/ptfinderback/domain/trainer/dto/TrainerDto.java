package com.ptfinder.ptfinderback.domain.trainer.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {

    private Long id;
    private Long userId;
    private Integer fee;
    private Float discountRate;
    private String introduction;
    private Long gymId;
}
