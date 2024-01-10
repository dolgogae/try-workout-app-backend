package com.tryworkout.backend.domain.fee.service;

import com.tryworkout.backend.domain.fee.dto.FeeCreateDto;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import com.tryworkout.backend.domain.trainer.repository.TrainerJpaRepository;
import com.tryworkout.backend.domain.fee.data.Fee;
import com.tryworkout.backend.domain.fee.dto.DiscountRateUpdateDto;
import com.tryworkout.backend.domain.fee.dto.FeeDto;
import com.tryworkout.backend.domain.fee.repository.FeeJpaRepository;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<FeeDto> getTrainerFees(Long trainerId) {
        List<Fee> feeList = feeJpaRepository.findByTrainer(trainerId);

        List<FeeDto> result = feeList.stream().map(fee -> {
            FeeDto feeDto = mapper.map(fee, FeeDto.class);
            feeDto.setTrainerId(fee.getTrainer().getId());
            return feeDto;
        }).toList();

        return result;
    }

    @Override
    public void deleteFee(Long feeId) {
        feeJpaRepository.deleteById(feeId);
    }
}
