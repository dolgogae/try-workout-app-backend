package com.tryworkout.backend.domain.fee.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DiscountRateUpdateDto {
    private Long feeId;
    private Float discountRate;
}
