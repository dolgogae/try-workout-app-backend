package com.tryworkout.backend.domain.reservation.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReservationCreateDto {
    private Long memberId;
    private Long trainerId;
    private LocalDateTime reservationTime;
}
