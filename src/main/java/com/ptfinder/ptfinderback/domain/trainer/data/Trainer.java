package com.ptfinder.ptfinderback.domain.trainer.data;

import com.ptfinder.ptfinderback.domain.fee.data.Fee;
import com.ptfinder.ptfinderback.domain.gym.data.Gym;
import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerCreateDto;
import com.ptfinder.ptfinderback.domain.user.data.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TRAINER")
@EntityListeners(AuditingEntityListener.class)
public class Trainer {

    @Id
    @Column(name = "TRAINER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", unique = true)
    private UserEntity user;

    @OneToMany(mappedBy = "trainer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Fee> fees = new ArrayList<>();

    @Column(name = "INTRODUCTION", length = 10000)
    private String introduction;

    @Column(name = "QUALIFICATIONS")
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> qualifications = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "GYM_ID")
    private Gym gym;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    private Trainer(UserEntity user, String introduction, Gym gym) {
        this.user = user;
        this.introduction = introduction;
        this.gym = gym;
    }

    public static Trainer create(TrainerCreateDto trainerDto, UserEntity user, Gym gym){
        return Trainer.builder()
                .introduction(trainerDto.getIntroduction())
                .user(user)
                .gym(gym)
                .build();
    }

    public Trainer updateGym(Gym gym){
        this.gym = gym;
        return this;
    }

}
