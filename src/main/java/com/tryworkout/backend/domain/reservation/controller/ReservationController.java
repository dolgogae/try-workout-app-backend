package com.tryworkout.backend.domain.reservation.controller;

import com.tryworkout.backend.domain.reservation.dto.ReservationContentsDto;
import com.tryworkout.backend.domain.reservation.dto.ReservationCreateDto;
import com.tryworkout.backend.domain.reservation.dto.ReservationDto;
import com.tryworkout.backend.domain.reservation.service.ReservationService;
import com.tryworkout.backend.global.response.ResultCode;
import com.tryworkout.backend.global.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ResultResponse> createReservation(
            @RequestBody ReservationCreateDto reservationCreateDto
    ){
        ReservationDto reservationDto = reservationService.createReservation(reservationCreateDto);

        ResultResponse result = ResultResponse.of(ResultCode.RESERVATION_REGISTER_SUCCESS, reservationDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<ResultResponse> getReservationsByMemberId(
            @PathVariable Long memberId,
            @RequestParam int page,
            @RequestParam int size
    ){
        Page<ReservationContentsDto> reservations = reservationService.getReservationsByMemberId(memberId, page, size);

        ResultResponse result = ResultResponse.of(ResultCode.FIND_RESERVATION_SUCCESS, reservations);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<ResultResponse> getReservationsByTrainerId(
            @PathVariable Long trainerId,
            @RequestParam int page,
            @RequestParam int size
    ){
        Page<ReservationContentsDto> reservations = reservationService.getReservationsByMemberId(trainerId, page, size);

        ResultResponse result = ResultResponse.of(ResultCode.FIND_RESERVATION_SUCCESS, reservations);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<ResultResponse> deleteReservation(
            @PathVariable Long reservationId
    ){
        ReservationDto reservationDto = reservationService.deleteReservation(reservationId);

        ResultResponse result = ResultResponse.of(ResultCode.RESERVATION_REGISTER_SUCCESS, reservationDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
