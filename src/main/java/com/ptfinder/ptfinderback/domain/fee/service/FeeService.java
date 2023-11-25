package com.ptfinder.ptfinderback.domain.fee.service;

import com.ptfinder.ptfinderback.domain.fee.dto.FeeCreateDto;
import com.ptfinder.ptfinderback.domain.fee.dto.FeeDto;
import com.ptfinder.ptfinderback.domain.fee.dto.FeeResponseDto;

public interface FeeService {

    FeeDto createFee(String trainerId, FeeCreateDto feeCreateDto);
    FeeDto updateDiscountRate(String feeId, Float discountRate);
}
