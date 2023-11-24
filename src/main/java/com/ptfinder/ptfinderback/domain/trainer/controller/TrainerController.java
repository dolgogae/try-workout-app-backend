package com.ptfinder.ptfinderback.domain.trainer.controller;

import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerDto;
import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerRequestDto;
import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerResponseDto;
import com.ptfinder.ptfinderback.domain.trainer.service.TrainerService;
import com.ptfinder.ptfinderback.global.result.ResultCode;
import com.ptfinder.ptfinderback.global.result.ResultResponse;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerService trainerService;
    private final ModelMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<ResultResponse> createTrainer(
            @RequestBody TrainerRequestDto trainerRequestDto
    ){
        log.info(String.valueOf(trainerRequestDto.getUserId()));
        TrainerDto trainerDto = mapper.map(trainerRequestDto, TrainerDto.class);
        TrainerDto trainer = trainerService.createTrainer(trainerDto);
        TrainerResponseDto trainerResponseDto = mapper.map(trainer, TrainerResponseDto.class);

        ResultResponse result = ResultResponse.of(ResultCode.TRAINER_CREATE_SUCCESS, trainerResponseDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{trainerId}/discount")
    public ResponseEntity<ResultResponse> updateDiscountRate(
            @RequestHeader String token,
            @PathVariable Long trainerId,
            @RequestBody Float discountRate
    ){
        TrainerDto trainerDto = trainerService.updateTrainerDiscountRate(trainerId, discountRate);
        TrainerResponseDto trainerResponseDto = mapper.map(trainerDto, TrainerResponseDto.class);

        ResultResponse result = ResultResponse.of(ResultCode.TRAINER_CREATE_SUCCESS, trainerResponseDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
