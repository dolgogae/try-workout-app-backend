package com.ptfinder.ptfinderback.domain.trainer.data;

import com.ptfinder.ptfinderback.domain.gym.data.Gym;
import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerDto;
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

    @Column(name = "FEE")
    private Integer fee;

    @Column(name = "INTRODUCTION", length = 10000)
    private String introduction;

    @Column(name = "QUALIFICATIONS")
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> qualifications = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GYM_ID")
    private Gym gym;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    private Trainer(UserEntity user, Integer fee, String introduction, Gym gym) {
        this.user = user;
        this.fee = fee;
        this.introduction = introduction;
        this.gym = gym;
    }

    public static Trainer create(TrainerDto trainerDto){
        return Trainer.builder()
                .fee(trainerDto.getFee())
                .introduction(trainerDto.getIntroduction())
                .build();
    }

    public Trainer updateUser(UserEntity user){
        this.user = user;
        return this;
    }
}
