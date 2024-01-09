package com.tryworkout.backend.domain.reservation.controller;

import com.tryworkout.backend.domain.reservation.dto.ReservationCreateDto;
import com.tryworkout.backend.domain.reservation.dto.ReservationDto;
import com.tryworkout.backend.domain.reservation.service.ReservationService;
import com.tryworkout.backend.global.result.ResultCode;
import com.tryworkout.backend.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
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

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<ResultResponse> deleteReservation(
            @PathVariable Long reservationId
    ){
        reservationService.deleteReservation(reservationId);

        ResultResponse result = ResultResponse.of(ResultCode.RESERVATION_REGISTER_SUCCESS, "delete success");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
