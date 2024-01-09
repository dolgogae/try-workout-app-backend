package com.tryworkout.backend.domain.reservation.data;

import com.tryworkout.backend.domain.member.data.Member;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "RESERVATION")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Reservation {


    @Id
    @Column(name = "RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

    @Column(name = "RESERVATION_TIME")
    private LocalDateTime reservationTime;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
