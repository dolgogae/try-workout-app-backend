package com.ptfinder.ptfinderback.domain.fee.controller;

import com.ptfinder.ptfinderback.domain.fee.dto.DiscountRateUpdateDto;
import com.ptfinder.ptfinderback.domain.fee.dto.FeeCreateDto;
import com.ptfinder.ptfinderback.domain.fee.dto.FeeDto;
import com.ptfinder.ptfinderback.domain.fee.dto.FeeResponseDto;
import com.ptfinder.ptfinderback.domain.fee.service.FeeService;
import com.ptfinder.ptfinderback.domain.user.service.UserProvider;
import com.ptfinder.ptfinderback.global.result.ResultCode;
import com.ptfinder.ptfinderback.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fee")
@RequiredArgsConstructor
public class FeeController {

    private final FeeService feeService;
    private final UserProvider userProvider;
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
