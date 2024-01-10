package com.tryworkout.backend.domain.review.data;

import com.tryworkout.backend.domain.member.data.Member;
import com.tryworkout.backend.domain.review.dto.ReviewCreateDto;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "REVIEW")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Review {

    @Id
    @Column(name = "REVIEW_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SCORE")
    private Integer score;

    @Column(name = "CONTENT", length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    private Review(Integer score, String content, Member member, Trainer trainer) {
        this.score = score;
        this.content = content;
        this.member = member;
        this.trainer = trainer;
    }

    public static Review create(ReviewCreateDto dto, Member member, Trainer trainer){
        return Review.builder()
                .score(dto.getScore())
                .content(dto.getContent())
                .member(member)
                .trainer(trainer)
                .build();
    }
}
