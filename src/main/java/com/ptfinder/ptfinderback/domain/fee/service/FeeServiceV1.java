package com.ptfinder.ptfinderback.domain.fee.service;

import com.ptfinder.ptfinderback.domain.fee.dto.FeeCreateDto;
import com.ptfinder.ptfinderback.domain.fee.dto.FeeDto;

public class FeeServiceV1 implements FeeService{
    @Override
    public FeeDto createFee(String trainerId, FeeCreateDto feeCreateDto) {
        return null;
    }

    @Override
    public FeeDto updateDiscountRate(String feeId, Float discountRate) {
        return null;
    }
}
