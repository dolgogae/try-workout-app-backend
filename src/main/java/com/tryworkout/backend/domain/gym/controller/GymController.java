package com.tryworkout.backend.domain.gym.controller;

import com.tryworkout.backend.domain.gym.dto.GymCreateDto;
import com.tryworkout.backend.domain.gym.dto.GymDto;
import com.tryworkout.backend.domain.gym.dto.GymLocationDto;
import com.tryworkout.backend.domain.gym.service.GymService;
import com.tryworkout.backend.domain.naverapi.dto.SearchLocalReq;
import com.tryworkout.backend.domain.naverapi.dto.SearchLocalRes;
import com.tryworkout.backend.domain.naverapi.service.NaverSearchClient;
import com.tryworkout.backend.global.result.ResultCode;
import com.tryworkout.backend.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/gym")
@RequiredArgsConstructor
public class GymController {

    private final GymService gymService;
    private final NaverSearchClient naverSearchClient;

    @PostMapping("/list")
    public ResponseEntity<ResultResponse> getGymFromNaverApi (
            @RequestBody String query
    ){
        SearchLocalReq searchLocalReq = SearchLocalReq.builder()
                .query(query)
                .build();

        SearchLocalRes searchLocalRes = naverSearchClient.searchLocal(searchLocalReq);

        ResultResponse result = ResultResponse.of(ResultCode.GET_GYM_SUCCESS, searchLocalRes);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
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

        ResultResponse result = ResultResponse.of(ResultCode.GET_GYM_SUCCESS, gymDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/nearest-points")
    public ResponseEntity<ResultResponse> getKNearestPoints (
        @RequestParam @NotBlank Float mapX,
        @RequestParam @NotBlank Float mapY,
        @RequestParam @NotBlank Integer k
    ){
        GymLocationDto gymLocationDto = GymLocationDto.builder()
                .mapX(mapX)
                .mapY(mapY).build();

        List<GymLocationDto> kNearestLocations = gymService.getKNearestLocations(k, gymLocationDto);

        ResultResponse result = ResultResponse.of(ResultCode.GET_NEAREST_GYM_SUCCESS, kNearestLocations);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
