package com.ptfinder.ptfinderback.domain.fee.service;

import com.ptfinder.ptfinderback.domain.fee.data.Fee;
import com.ptfinder.ptfinderback.domain.fee.dto.DiscountRateUpdateDto;
import com.ptfinder.ptfinderback.domain.fee.dto.FeeCreateDto;
import com.ptfinder.ptfinderback.domain.fee.dto.FeeDto;
import com.ptfinder.ptfinderback.domain.fee.repository.FeeJpaRepository;
import com.ptfinder.ptfinderback.domain.trainer.data.Trainer;
import com.ptfinder.ptfinderback.domain.trainer.repository.TrainerJpaRepository;
import com.ptfinder.ptfinderback.global.error.ErrorCode;
import com.ptfinder.ptfinderback.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service @Slf4j
@RequiredArgsConstructor
public class FeeServiceV1 implements FeeService{

    private final FeeJpaRepository feeJpaRepository;
    private final TrainerJpaRepository trainerJpaRepository;
    private final ModelMapper mapper;

    @Override
    public FeeDto createFee(FeeCreateDto feeCreateDto) {
        Trainer trainer = trainerJpaRepository.findById(feeCreateDto.getTrainerId()).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        if(feeCreateDto.getDiscountRate() == null){
            feeCreateDto.setDiscountRate(0F);
        }

        Fee fee = Fee.create(feeCreateDto, trainer);
        Fee savedFee = feeJpaRepository.save(fee);

        FeeDto result = mapper.map(savedFee, FeeDto.class);
        result.setTrainerId(feeCreateDto.getTrainerId());

        return result;
    }

    @Override
    public FeeDto updateDiscountRate(DiscountRateUpdateDto discountRateUpdateDto) {
        Fee fee = feeJpaRepository.findById(discountRateUpdateDto.getFeeId()).orElseThrow(() ->
                new BusinessException(ErrorCode.FEE_NOT_FOUND));

        Fee updatedFee = fee.updateDiscountRate(discountRateUpdateDto.getDiscountRate());

        FeeDto result = mapper.map(updatedFee, FeeDto.class);
        result.setTrainerId(updatedFee.getTrainer().getId());

        return result;
    }

    @Override
    public void deleteFee(Long feeId) {
        feeJpaRepository.deleteById(feeId);
    }
}
