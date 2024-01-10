package com.tryworkout.backend.domain.fee.controller;

import com.tryworkout.backend.domain.fee.dto.DiscountRateUpdateDto;
import com.tryworkout.backend.domain.fee.dto.FeeCreateDto;
import com.tryworkout.backend.domain.fee.dto.FeeDto;
import com.tryworkout.backend.domain.fee.dto.FeeResponseDto;
import com.tryworkout.backend.domain.fee.service.FeeService;
import com.tryworkout.backend.global.result.ResultCode;
import com.tryworkout.backend.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee")
@RequiredArgsConstructor
public class FeeController {

    private final FeeService feeService;
    private final ModelMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_TRAINER')")
    public ResponseEntity<ResultResponse> createFee(
            @RequestBody FeeCreateDto feeCreateDto
    ){
        FeeDto fee = feeService.createFee(feeCreateDto);
        FeeResponseDto feeResponseDto = mapper.map(fee, FeeResponseDto.class);

        ResultResponse result = ResultResponse.of(ResultCode.FEE_CREATE_SUCCESS, feeResponseDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{tarinerId}")
    @PreAuthorize("hasRole('ROLE_TRAINER')")
    public ResponseEntity<ResultResponse> getFees(
            @PathVariable Long tarinerId
    ){
        List<FeeDto> trainerFees = feeService.getTrainerFees(tarinerId);

        ResultResponse result = ResultResponse.of(ResultCode.FEE_READ_SUCCESS, trainerFees);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_TRAINER')")
    public ResponseEntity<ResultResponse> updateDiscountRate(
            @RequestBody DiscountRateUpdateDto updateDto
    ){
        FeeDto feeDto = feeService.updateDiscountRate(updateDto);
        FeeResponseDto feeResponseDto = mapper.map(feeDto, FeeResponseDto.class);

        ResultResponse result = ResultResponse.of(ResultCode.FEE_UPDATE_SUCCESS, feeResponseDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_TRAINER')")
    public ResponseEntity<ResultResponse> updateDiscountRate(
            @RequestBody Long feeId
    ){
        feeService.deleteFee(feeId);

        ResultResponse result = ResultResponse.of(ResultCode.FEE_DELETE_SUCCESS, feeId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
