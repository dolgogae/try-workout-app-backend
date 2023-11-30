package com.ptfinder.ptfinderback.domain.gym.controller;

import com.ptfinder.ptfinderback.domain.gym.dto.GymCreateDto;
import com.ptfinder.ptfinderback.domain.gym.dto.GymDto;
import com.ptfinder.ptfinderback.domain.gym.service.GymService;
import com.ptfinder.ptfinderback.global.result.ResultCode;
import com.ptfinder.ptfinderback.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Slf4j
@RestController
@RequestMapping("/gym")
@RequiredArgsConstructor
public class GymController {

    private final GymService gymService;

    @GetMapping("")
    public ResponseEntity<ResultResponse> getGym (
        @RequestParam @NotBlank String gymName,
        @RequestParam @NotBlank Float mapX,
        @RequestParam @NotBlank Float mapY,
        @RequestParam(required = false) String address,
        @RequestParam(required = false) String roadAddress
    ){

        GymCreateDto gymCreateDto = GymCreateDto.builder()
                .gymName(gymName)
                .address(address)
                .roadAddress(roadAddress)
                .mapX(mapX)
                .mapY(mapY)
                .build();

        GymDto gymDto = gymService.getGym(gymCreateDto);

        ResultResponse result = ResultResponse.of(ResultCode.TRAINER_CREATE_SUCCESS, gymDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
