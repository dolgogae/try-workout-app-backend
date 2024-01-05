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
@Table(name = "QAULIFICATION")
@EntityListeners(AuditingEntityListener.class)
public class Qualification {

    @Id
    @Column(name = "QAULIFICATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "URL")
    private String url;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    private Qualification(String url, Trainer trainer, String name) {
        this.url = url;
        this.trainer = trainer;
        this.name = name;
    }

    public static Qualification create(ImageCreateDto<Trainer> dto){
        return Qualification.builder()
                .name(dto.getName())
                .trainer(dto.getEntity())
                .url(dto.getUrl())
                .build();
    }
}
