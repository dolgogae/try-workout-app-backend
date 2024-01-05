package com.tryworkout.backend.domain.filestorage.service.strategy;

import com.tryworkout.backend.domain.filestorage.dto.ImageCreateDto;

public interface EntityCreationStrategy<T> {
    T createEntity(ImageCreateDto<?> dto);
    Class<T> getEntityType();
}
