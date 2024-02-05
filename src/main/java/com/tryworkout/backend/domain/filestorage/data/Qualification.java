package com.tryworkout.backend.domain.filestorage.data;

import com.tryworkout.backend.domain.BaseEntity;
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
@Table(name = "QAULIFICATION")
@EntityListeners(AuditingEntityListener.class)
public class Qualification extends BaseEntity {

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
