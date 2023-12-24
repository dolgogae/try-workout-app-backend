package com.tryworkout.backend.domain.trainer.service;

import com.tryworkout.backend.domain.trainer.dto.TrainerCreateDto;
import com.tryworkout.backend.domain.trainer.dto.TrainerDto;

public interface TrainerService {

    TrainerDto createTrainer(TrainerCreateDto trainerCreateDto);
    TrainerDto updateGym(Long trainerId, Long gymId);
}
