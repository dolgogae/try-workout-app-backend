package com.tryworkout.backend.domain.reservation.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@NoArgsConstructor
public class ReservationContentsDto {
    private Long id;
    private Long memberId;
    private String memberName;
    private Long trainerId;
    private String trainerName;
    private LocalDateTime reservationTime;

    public ReservationContentsDto(Long id, Long memberId, String memberName, Long trainerId, String trainerName, LocalDateTime reservationTime) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.reservationTime = reservationTime;
    }
}
