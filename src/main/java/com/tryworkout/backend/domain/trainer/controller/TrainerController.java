package com.tryworkout.backend.domain.trainer.controller;

import com.tryworkout.backend.domain.filestorage.dto.ImageCreateRequestDto;
import com.tryworkout.backend.domain.filestorage.dto.TrainerImageResponse;
import com.tryworkout.backend.domain.filestorage.service.FileStorageService;
import com.tryworkout.backend.domain.trainer.service.TrainerService;
import com.tryworkout.backend.domain.trainer.dto.TrainerCreateDto;
import com.tryworkout.backend.domain.trainer.dto.TrainerDto;
import com.tryworkout.backend.domain.trainer.dto.TrainerResponseDto;
import com.tryworkout.backend.domain.user.service.UserProvider;
import com.tryworkout.backend.global.result.ResultCode;
import com.tryworkout.backend.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerService trainerService;
    private final ModelMapper mapper;
    private final UserProvider userProvider;
    private final FileStorageService fileStorageService;

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

    @PostMapping("/{trainerId}/images")
    @PreAuthorize("hasRole('ROLE_TRAINER')")
    public ResponseEntity<ResultResponse> createTrainerImage(
            @PathVariable Long trainerId,
            @RequestBody ImageCreateRequestDto imageCreateRequestDto
    ){
        imageCreateRequestDto.setId(trainerId);
        TrainerImageResponse trainerImage = fileStorageService.createTrainerImage(imageCreateRequestDto);

        ResultResponse result = ResultResponse.of(ResultCode.IMAGE_UPLOAD_SUCCESS , trainerImage);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
