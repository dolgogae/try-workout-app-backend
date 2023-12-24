package com.tryworkout.backend.domain.fee.service;

import com.tryworkout.backend.domain.fee.dto.DiscountRateUpdateDto;
import com.tryworkout.backend.domain.fee.dto.FeeCreateDto;
import com.tryworkout.backend.domain.fee.dto.FeeDto;

public interface FeeService {

    FeeDto createFee(FeeCreateDto feeCreateDto);
    FeeDto updateDiscountRate(DiscountRateUpdateDto discountRateUpdateDto);
    void deleteFee(Long feeId);
}
