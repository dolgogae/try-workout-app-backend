package com.tryworkout.backend.domain.filestorage.service.strategy;

import com.tryworkout.backend.domain.filestorage.data.TrainerImage;
import com.tryworkout.backend.domain.filestorage.dto.ImageCreateDto;
import com.tryworkout.backend.domain.filestorage.repository.TrainerImageJpaRepository;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainerImageCreationStrategy implements EntityCreationStrategy<TrainerImage> {

    private final TrainerImageJpaRepository trainerImageJpaRepository;

    @Override
    public TrainerImage createEntity(ImageCreateDto<?> dto) {
        TrainerImage trainerImage = null;
        if(dto.getEntity() instanceof Trainer){
            trainerImage = TrainerImage.create((ImageCreateDto<Trainer>) dto);
        } else {
            throw new BusinessException(ErrorCode.IMAGE_TYPE_NOT_MATCHING);
        }

        TrainerImage savedTrainerImage = trainerImageJpaRepository.save(trainerImage);

        return savedTrainerImage;
    }

    @Override
    public Class<TrainerImage> getEntityType() {
        return TrainerImage.class;
    }
}
