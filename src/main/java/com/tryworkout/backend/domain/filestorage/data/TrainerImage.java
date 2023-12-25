package com.tryworkout.backend.domain.filestorage.data;

import com.tryworkout.backend.domain.filestorage.dto.ImageCreateDto;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "IMAGE")
@EntityListeners(AuditingEntityListener.class)
public class TrainerImage {

    @Id
    @Column(name = "IMAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "URL")
    private String url;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Trainer trainer;

    @Builder
    private TrainerImage(String url, Trainer trainer) {
        this.url = url;
        this.trainer = trainer;
    }

    public static TrainerImage create(ImageCreateDto<Trainer> dto){
        return TrainerImage.builder()
                .trainer(dto.getEntity())
                .url(dto.getUrl())
                .build();
    }
}
