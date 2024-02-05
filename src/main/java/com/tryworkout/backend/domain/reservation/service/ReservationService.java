package com.tryworkout.backend.domain.reservation.service;

import com.tryworkout.backend.domain.member.data.Member;
import com.tryworkout.backend.domain.member.repository.MemberJpaRepository;
import com.tryworkout.backend.domain.reservation.data.Reservation;
import com.tryworkout.backend.domain.reservation.dto.ReservationContentsDto;
import com.tryworkout.backend.domain.reservation.dto.ReservationCreateDto;
import com.tryworkout.backend.domain.reservation.dto.ReservationDto;
import com.tryworkout.backend.domain.reservation.repository.ReservationDslRepository;
import com.tryworkout.backend.domain.reservation.repository.ReservationJpaRepository;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import com.tryworkout.backend.domain.trainer.repository.TrainerJpaRepository;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final MemberJpaRepository memberJpaRepository;
    private final TrainerJpaRepository trainerJpaRepository;
    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationDslRepository reservationDslRepository;

    public ReservationDto createReservation(ReservationCreateDto createDto) {

        Member member = memberJpaRepository.findById(createDto.getMemberId()).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));
        Trainer trainer = trainerJpaRepository.findById(createDto.getTrainerId()).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        Reservation reservation = Reservation.create(member, trainer, createDto.getReservationTime());

        Reservation savedReservation = reservationJpaRepository.save(reservation);

        ReservationDto result = ReservationDto.builder()
                .id(savedReservation.getId())
                .reservationTime(savedReservation.getReservationTime())
                .trainerId(savedReservation.getTrainer().getId())
                .memberId(savedReservation.getMember().getId())
                .build();

        return result;
    }

    public ReservationDto deleteReservation(Long reservationId){
        Reservation reservation = reservationJpaRepository.findById(reservationId).orElseThrow(() ->
                new BusinessException(ErrorCode.RESERVATION_NOT_EXIST));

        ReservationDto reservationDto = ReservationDto.builder()
                .id(reservation.getId())
                .reservationTime(reservation.getReservationTime())
                .trainerId(reservation.getTrainer().getId())
                .memberId(reservation.getMember().getId())
                .build();
        log.info(String.valueOf(reservationDto));

        reservationJpaRepository.deleteById(reservationId);

        return reservationDto;
    }

    public Page<ReservationContentsDto> getReservationsByMemberId(Long memberId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<ReservationContentsDto> reservationContents
                = reservationDslRepository.findReservationsByMemberId(memberId, pageable);

        return reservationContents;
    }

    public Page<ReservationContentsDto> getReservationsByTrainerId(Long trainerId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReservationContentsDto> reservationContents
                = reservationDslRepository.findReservationsByTrainerId(trainerId, pageable);

        return reservationContents;
    }
}
