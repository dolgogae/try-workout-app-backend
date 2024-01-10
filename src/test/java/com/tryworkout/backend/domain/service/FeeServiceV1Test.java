package com.tryworkout.backend.domain.service;

import com.tryworkout.backend.domain.fee.data.Fee;
import com.tryworkout.backend.domain.fee.dto.FeeCreateDto;
import com.tryworkout.backend.domain.fee.dto.FeeDto;
import com.tryworkout.backend.domain.fee.repository.FeeJpaRepository;
import com.tryworkout.backend.domain.fee.service.FeeService;
import com.tryworkout.backend.domain.fee.service.FeeServiceV1;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import com.tryworkout.backend.domain.trainer.repository.TrainerJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@SpringBootTest
@Transactional
class FeeServiceV1Test {

    @Autowired
    FeeService feeService;
    @Mock
    FeeJpaRepository feeJpaRepository;
    @Mock
    TrainerJpaRepository trainerJpaRepository;
    @Autowired
    ModelMapper mapper;

    FeeService feeServiceMocked;

    Fee fee;
    Trainer trainer;

    @BeforeEach
    void before(){
        trainer = new Trainer();
        setField(trainer, "id", 1L);
        feeServiceMocked = new FeeServiceV1(
                feeJpaRepository, trainerJpaRepository, mapper);
        FeeCreateDto feeCreateDto = FeeCreateDto.builder()
                .times(10)
                .price(700000)
                .discountRate(0.2F)
                .trainerId(1L)
                .build();
        fee = Fee.create(feeCreateDto, trainer);
    }

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

    @Test
    @DisplayName("트레이너 요금 조회")
    public void getTrainerFeesTest(){
        // given
        when(feeJpaRepository.findByTrainer(1L))
                .thenReturn(new ArrayList<>(List.of(fee)));

        // when
        List<FeeDto> result = feeServiceMocked.getTrainerFees(1L);

        // then
        assertEquals(1, result.size());
        FeeDto feeDto = result.get(0);
        assertEquals(1L, feeDto.getTrainerId());
        assertEquals(10, feeDto.getTimes());
        assertEquals(700000, feeDto.getPrice());
    }
}