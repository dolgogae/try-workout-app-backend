package com.ptfinder.ptfinderback.domain.trainer.controller;

import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerCreateDto;
import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerDto;
import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerResponseDto;
import com.ptfinder.ptfinderback.domain.trainer.service.TrainerService;
import com.ptfinder.ptfinderback.domain.user.service.UserProvider;
import com.ptfinder.ptfinderback.global.result.ResultCode;
import com.ptfinder.ptfinderback.global.result.ResultResponse;
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
    private final UserProvider userProvider;

    @PostMapping
    public ResponseEntity<ResultResponse> createTrainer(
            @RequestBody TrainerCreateDto trainerCreateDto
    ){
        log.info(String.valueOf(trainerCreateDto.getUserId()));
        TrainerDto trainer = trainerService.createTrainer(trainerCreateDto);
        TrainerResponseDto trainerResponseDto = mapper.map(trainer, TrainerResponseDto.class);

        ResultResponse result = ResultResponse.of(ResultCode.TRAINER_CREATE_SUCCESS, trainerResponseDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{trainerId}/gym/{gymId}")
    public ResponseEntity<ResultResponse> updateTrainersGym(
            @RequestHeader String token,
            @PathVariable Long trainerId,
            @PathVariable Long gymId
    ){
        TrainerDto trainerDto = trainerService.updateGym(trainerId, gymId);

        ResultResponse result = ResultResponse.of(ResultCode.TRAINER_UPDATE_SUCCESS, trainerDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
