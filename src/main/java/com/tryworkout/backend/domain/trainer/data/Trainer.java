package com.tryworkout.backend.domain.trainer.data;

import com.tryworkout.backend.domain.gym.data.Gym;
import com.tryworkout.backend.domain.fee.data.Fee;
import com.tryworkout.backend.domain.filestorage.data.TrainerImage;
import com.tryworkout.backend.domain.filestorage.data.Qualification;
import com.tryworkout.backend.domain.trainer.TrainerType;
import com.tryworkout.backend.domain.trainer.dto.TrainerCreateDto;
import com.tryworkout.backend.domain.user.data.UserEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
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

    @OneToMany(mappedBy = "trainer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Qualification> qualifications = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "GYM_ID")
    private Gym gym;

    @Column(name = "TRAINER_TYPE")
    @Enumerated(value = EnumType.STRING)
    private TrainerType trainerType;

    @OneToMany(mappedBy = "trainer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TrainerImage> trainerImages = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    private Trainer(UserEntity user, String introduction, Gym gym, TrainerType trainerType) {
        this.user = user;
        this.introduction = introduction;
        this.gym = gym;
        this.trainerType = trainerType;
    }

    public static Trainer create(TrainerCreateDto trainerDto, UserEntity user, Gym gym){
        return Trainer.builder()
                .introduction(trainerDto.getIntroduction())
                .user(user)
                .gym(gym)
                .trainerType(TrainerType.valueOf(trainerDto.getTrainerType()))
                .build();
    }

    public Trainer registerGym(Gym gym){
        this.gym = gym;
        return this;
    }

}
