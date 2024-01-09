package com.tryworkout.backend.domain.reservation.service;

import com.tryworkout.backend.domain.member.data.Member;
import com.tryworkout.backend.domain.member.repository.MemberJpaRepository;
import com.tryworkout.backend.domain.reservation.data.Reservation;
import com.tryworkout.backend.domain.reservation.dto.ReservationCreateDto;
import com.tryworkout.backend.domain.reservation.dto.ReservationDto;
import com.tryworkout.backend.domain.reservation.repository.ReservationJpaRepository;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import com.tryworkout.backend.domain.trainer.repository.TrainerJpaRepository;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceV1 implements ReservationService{

    private final MemberJpaRepository memberJpaRepository;
    private final TrainerJpaRepository trainerJpaRepository;
    private final ReservationJpaRepository reservationJpaRepository;

    @Override
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

    @Override
    public void deleteReservation(Long reservationId){
        reservationJpaRepository.deleteById(reservationId);
    }
}
