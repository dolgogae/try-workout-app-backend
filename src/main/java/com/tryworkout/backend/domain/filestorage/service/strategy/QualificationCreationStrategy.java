package com.tryworkout.backend.domain.filestorage.service.strategy;

import com.tryworkout.backend.domain.filestorage.data.Qualification;
import com.tryworkout.backend.domain.filestorage.dto.ImageCreateDto;
import com.tryworkout.backend.domain.filestorage.repository.QualificationJpaRepository;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QualificationCreationStrategy implements EntityCreationStrategy<Qualification> {

    private final QualificationJpaRepository qualificationJpaRepository;

    @Override
    public Qualification createEntity(ImageCreateDto<?> dto) {
        Qualification qualification = null;
        if(dto.getEntity() instanceof Trainer){
            qualification = Qualification.create((ImageCreateDto<Trainer>) dto);
        } else {
            throw new BusinessException(ErrorCode.IMAGE_TYPE_NOT_MATCHING);
        }

        Qualification savedQualification = qualificationJpaRepository.save(qualification);

        return savedQualification;
    }

    @Override
    public Class<Qualification> getEntityType() {
        return Qualification.class;
    }
}