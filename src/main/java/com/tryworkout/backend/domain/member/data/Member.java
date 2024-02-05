package com.tryworkout.backend.domain.member.data;

import com.tryworkout.backend.domain.BaseEntity;
import com.tryworkout.backend.domain.reservation.data.Reservation;
import com.tryworkout.backend.domain.review.data.Review;
import com.tryworkout.backend.domain.user.data.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "MEMBER")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Member extends BaseEntity {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", unique = true)
    private UserEntity user;

    @Column(name = "EXERCISE_PERIOD_YEAR")
    private Integer exercisePeriodYear;

    @OneToMany(mappedBy = "member",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Reservation> reservations = new ArrayList<>();

    @Builder
    private Member(UserEntity user, Integer exercisePeriodYear) {
        this.user = user;
        this.exercisePeriodYear = exercisePeriodYear;
    }
    public static Member create(UserEntity user, Integer exercisePeriodYear){
        return Member.builder()
                .user(user)
                .exercisePeriodYear(exercisePeriodYear)
                .build();
    }
}