package com.tryworkout.backend.domain.fee.service;

import com.tryworkout.backend.domain.fee.dto.DiscountRateUpdateDto;
import com.tryworkout.backend.domain.fee.dto.FeeCreateDto;
import com.tryworkout.backend.domain.fee.dto.FeeDto;

import java.util.List;

public interface FeeService {

    FeeDto createFee(FeeCreateDto feeCreateDto);
    FeeDto updateDiscountRate(DiscountRateUpdateDto discountRateUpdateDto);
    List<FeeDto> getTrainerFees(Long trainerId);
    void deleteFee(Long feeId);
}
