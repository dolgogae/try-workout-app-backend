package com.ptfinder.ptfinderback.domain.fee.service;

import com.tryworkout.backend.domain.fee.dto.FeeCreateDto;
import com.tryworkout.backend.domain.fee.dto.FeeDto;
import com.tryworkout.backend.domain.fee.service.FeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class FeeServiceV1Test {

    @Autowired
    FeeService feeService;

    @Test
    @DisplayName("요금 추가 서비스 테스트")
    public void createFeeTest(){
        // given
        FeeCreateDto feeCreateDto = FeeCreateDto.builder()
                .times(10)
                .price(700000)
                .discountRate(0.2F)
                .trainerId(1L)
                .build();

        // when
        FeeDto fee = feeService.createFee(feeCreateDto);

        // then
        assertThat(fee.getTimes()).isEqualTo(feeCreateDto.getTimes());
        assertThat(fee.getPrice()).isEqualTo(feeCreateDto.getPrice());
        assertThat(fee.getTrainerId()).isEqualTo(feeCreateDto.getTrainerId());

    }
}