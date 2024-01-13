package com.tryworkout.backend.domain.reservation.repository;

import com.tryworkout.backend.domain.reservation.data.Reservation;
import com.tryworkout.backend.domain.reservation.dto.ReservationDto;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT new com.tryworkout.backend.domain.reservation.dto.ReservationContentsDto(r.id, " +
            "r.member.id, r.member.user.username, " +
            "r.trainer.id, r.trainer.user.username, " +
            "r.reservationTime)" +
            " FROM Reservation r WHERE r.member.id = :userId ORDER BY r.createdAt DESC")
    Page<ReservationDto> findAllByMemberOrderByCreatedAtDesc(@Param("userId") Long userId, Pageable pageable);

}
