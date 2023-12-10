package com.ptfinder.ptfinderback.domain.fee.service;

import com.ptfinder.ptfinderback.domain.fee.dto.DiscountRateUpdateDto;
import com.ptfinder.ptfinderback.domain.fee.dto.FeeCreateDto;
import com.ptfinder.ptfinderback.domain.fee.dto.FeeDto;

public interface FeeService {

    FeeDto createFee(FeeCreateDto feeCreateDto);
    FeeDto updateDiscountRate(DiscountRateUpdateDto discountRateUpdateDto);
    void deleteFee(Long feeId);
}
