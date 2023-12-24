package com.tryworkout.backend.domain.fee.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FeeCreateDto {

    private Integer times;
    private Integer price;
    private Float discountRate;
    private Long trainerId;
}
