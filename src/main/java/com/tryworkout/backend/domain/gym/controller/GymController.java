package com.tryworkout.backend.domain.gym.controller;

import com.tryworkout.backend.domain.gym.dto.*;
import com.tryworkout.backend.domain.gym.service.GymService;
import com.tryworkout.backend.domain.naverapi.dto.SearchLocalReq;
import com.tryworkout.backend.domain.naverapi.dto.SearchLocalRes;
import com.tryworkout.backend.domain.naverapi.service.NaverSearchClient;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import com.tryworkout.backend.global.response.ResultCode;
import com.tryworkout.backend.global.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     *
     * @param query :
     * @return
     */
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

    @PutMapping("/{gymId}/fees")
    @PreAuthorize("hasRole('ROLE_TRAINER')")
    public ResponseEntity<ResultResponse> updateGymFees(
            @RequestBody GymFeeUpdateDto gymFeeUpdateDto
    ){
        checkUpdateAuth(gymFeeUpdateDto.getTrainerId(), gymFeeUpdateDto.getGymId());
        GymDto gymDto = gymService.updateGymFees(gymFeeUpdateDto);

        ResultResponse result = ResultResponse.of(ResultCode.GYM_UPDATE_SUCCESS, gymDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{gymId}/operation-time")
    @PreAuthorize("hasRole('ROLE_TRAINER')")
    public ResponseEntity<ResultResponse> updateGymOperationTime(
            @RequestBody GymOperatingTimeDto gymOperatingTimeDto
    ){
        checkUpdateAuth(gymOperatingTimeDto.getTrainerId(), gymOperatingTimeDto.getGymId());
        GymDto gymDto = gymService.updateGymOperatingTime(gymOperatingTimeDto);

        ResultResponse result = ResultResponse.of(ResultCode.GYM_UPDATE_SUCCESS, gymDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{gymId}/information")
    @PreAuthorize("hasRole('ROLE_TRAINER')")
    public ResponseEntity<ResultResponse> updateGymInformation(
            @RequestBody GymInfoUpdateDto gymInfoUpdateDto
    ){
        checkUpdateAuth(gymInfoUpdateDto.getTrainerId(), gymInfoUpdateDto.getGymId());
        GymDto gymDto = gymService.updateGymInformation(gymInfoUpdateDto);

        ResultResponse result = ResultResponse.of(ResultCode.GYM_UPDATE_SUCCESS, gymDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private void checkUpdateAuth(Long gymInfoUpdateDto, Long gymInfoUpdateDto1) {
        if (!gymService.isGymUpdateAuth(gymInfoUpdateDto, gymInfoUpdateDto1)) {
            throw new BusinessException(ErrorCode.AUTHENTICATION_NOT_FOUND);
        }
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
