package com.tryworkout.backend.domain.reservation.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tryworkout.backend.domain.member.data.QMember;
import com.tryworkout.backend.domain.reservation.data.QReservation;
import com.tryworkout.backend.domain.reservation.dto.ReservationContentsDto;
import com.tryworkout.backend.domain.trainer.data.QTrainer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationDslRepository {

    private final JPAQueryFactory queryFactory;


    public Page<ReservationContentsDto> findReservationsByMemberId(Long memberId, Pageable pageable) {
        QMember member = QMember.member;
        BooleanExpression condition = member.id.eq(memberId);
        return findReservations(condition, pageable);
    }

    public Page<ReservationContentsDto> findReservationsByTrainerId(Long trainerId, Pageable pageable) {
        QTrainer trainer = QTrainer.trainer;
        BooleanExpression condition = trainer.id.eq(trainerId);
        return findReservations(condition, pageable);
    }

    private Page<ReservationContentsDto> findReservations(BooleanExpression condition, Pageable pageable) {
        QReservation reservation = QReservation.reservation;
        QMember member = QMember.member;
        QTrainer trainer = QTrainer.trainer;

        List<ReservationContentsDto> content = queryFactory
                .select(Projections.constructor(ReservationContentsDto.class,
                        reservation.id,
                        member.id,
                        member.user.username,
                        trainer.id,
                        trainer.user.username,
                        reservation.reservationTime))
                .from(reservation)
                .leftJoin(reservation.member, member).fetchJoin()
                .leftJoin(reservation.trainer, trainer).fetchJoin()
                .where(condition)
                .orderBy(reservation.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(reservation)
                .leftJoin(reservation.member, member).fetchJoin()
                .where(condition)
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }
}
