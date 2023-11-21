package com.ptfinder.ptfinderback.domain.trainer.data;

import com.ptfinder.ptfinderback.domain.gym.data.Gym;
import com.ptfinder.ptfinderback.domain.review.data.Review;
import com.ptfinder.ptfinderback.domain.user.data.UserEntity;
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

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(name = "FEE")
    private Integer fee;

    @Column(name = "DISCOUNT_RATE")
    private Float discountRate;

    @Column(name = "INTRODUCTION", length = 10000)
    private String introduction;

    @Column(name = "QUALIFICATIONS")
    @ElementCollection
    private List<String> qualifications;

    @OneToMany(mappedBy = "trainer")
    private List<Review> reviews = new ArrayList<>();

//    @Column(name = "PROGRAM_TITLE")
//    private String programTitle;
//
//    @Column(name = "PROGRAM_CONTENT", length = 10000)
//    private String programContent;

    @ManyToOne
    @Column(name = "GYM_ID")
    private Gym gym;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
