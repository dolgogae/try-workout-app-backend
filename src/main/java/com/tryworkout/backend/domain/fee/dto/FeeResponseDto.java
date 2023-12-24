package com.tryworkout.backend.domain.fee.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FeeResponseDto {

    private Integer times;
    private Integer price;
    private Float discountRate;
    private Float getDiscountRate;
    private Long trainerId;
}
