package com.tryworkout.backend.unit.domain.fee.controller;

import com.tryworkout.backend.domain.fee.controller.FeeController;
import com.tryworkout.backend.domain.fee.dto.DiscountRateUpdateDto;
import com.tryworkout.backend.domain.fee.dto.FeeCreateDto;
import com.tryworkout.backend.domain.fee.dto.FeeDto;
import com.tryworkout.backend.domain.fee.dto.FeeResponseDto;
import com.tryworkout.backend.domain.fee.service.FeeService;
import com.tryworkout.backend.global.response.ResultResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class FeeControllerTest {

    @MockBean
    FeeService feeService;
    @Autowired
    ModelMapper mapper;

    FeeController feeController;

    @BeforeEach
    void before(){
        feeController = new FeeController(feeService, mapper);
    }

    @Test
    @DisplayName("요금 생성 컨트롤러 테스트")
    public void createFeeTest(){
        // given
        FeeCreateDto feeCreateDto = FeeCreateDto.builder()
                .times(1)
                .price(10000)
                .discountRate(0.1F)
                .trainerId(1L)
                .build();
        FeeDto feeDto = FeeDto.builder()
                .times(1)
                .price(10000)
                .discountRate(0.1F)
                .trainerId(1L)
                .build();
        when(feeService.createFee(any())).thenReturn(feeDto);
        FeeResponseDto feeResponseDto = mapper.map(feeDto, FeeResponseDto.class);

        // when
        ResponseEntity<ResultResponse> result = feeController.createFee(feeCreateDto);

        // then
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

//        System.out.println(result.getBody());
        assertEquals(feeResponseDto, result.getBody().getData());
        assertEquals(201, result.getBody().getStatus());
        assertEquals("F001", result.getBody().getCode());
        assertEquals("요금 정보 생성을 성공했습니다.", result.getBody().getMessage());

    }

    @Test
    @DisplayName("요금 조회 API 테스트")
    public void getFeesTest(){
        // given
        Long trainerId = 1L;
        ArrayList<FeeDto> feeDtos = new ArrayList<>(List.of(FeeDto.builder()
                .times(1)
                .price(10000)
                .discountRate(0.1F)
                .trainerId(1L)
                .build()));
        when(feeService.getTrainerFees(trainerId)).thenReturn(feeDtos);

        // when
        ResponseEntity<ResultResponse> result = feeController.getFees(trainerId);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        ResultResponse body = result.getBody();
        assertEquals(200, body.getStatus());
        assertEquals("F002", body.getCode());
        assertEquals("요금 정보 조회를 성공했습니다.", body.getMessage());
        assertEquals(feeDtos, body.getData());
    }

    @Test
    @DisplayName("요금 할인율 업데이트 API 테스트")
    public void updateDiscountRateTest(){
        // given
        DiscountRateUpdateDto updateDto = DiscountRateUpdateDto.builder()
                .feeId(1L)
                .discountRate(0.1F)
                .build();
        FeeDto feeDto = FeeDto.builder()
                .times(1)
                .price(10000)
                .discountRate(0.1F)
                .trainerId(1L)
                .build();
        when(feeService.updateDiscountRate(updateDto))
                .thenReturn(feeDto);
        FeeResponseDto feeResponseDto = mapper.map(feeDto, FeeResponseDto.class);

        // when
        ResponseEntity<ResultResponse> result = feeController.updateDiscountRate(updateDto);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());

        ResultResponse body = result.getBody();
        assertEquals(200, body.getStatus());
        assertEquals("F003", body.getCode());
        assertEquals("요금 정보 업데이트를 성공했습니다.", body.getMessage());
        assertEquals(feeResponseDto, body.getData());
    }

    @Test
    @DisplayName("요금 할인율 삭제 API 테스트")
    public void deleteDiscountRateTest(){
        // given
        Long feeId = 1L;
        doNothing().when(feeService).deleteFee(feeId);

        // when
        ResponseEntity<ResultResponse> result = feeController.deleteDiscountRate(feeId);

        // then
        ResultResponse body = result.getBody();
        assertEquals(200, body.getStatus());
        assertEquals("F004", body.getCode());
        assertEquals("요금 삭제를 성공했습니다.", body.getMessage());
        assertEquals(feeId, body.getData());
    }
}