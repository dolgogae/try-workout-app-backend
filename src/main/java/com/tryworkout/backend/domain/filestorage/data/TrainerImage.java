package com.tryworkout.backend.domain.filestorage.data;

import com.tryworkout.backend.domain.filestorage.dto.ImageCreateDto;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TRAINER_IMAGE")
@EntityListeners(AuditingEntityListener.class)
public class TrainerImage {

    @Id
    @Column(name = "TRAINER_IMAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "URL")
    private String url;

    @Column(name = "EXPLANATION")
    private String explanation;

    @ManyToOne
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    private TrainerImage(String url, Trainer trainer, String explanation) {
        this.url = url;
        this.trainer = trainer;
        this.explanation = explanation;
    }

    public static TrainerImage create(ImageCreateDto<Trainer> dto){
        return TrainerImage.builder()
                .trainer(dto.getEntity())
                .url(dto.getUrl())
                .explanation(dto.getExplanation())
                .build();
    }
}
