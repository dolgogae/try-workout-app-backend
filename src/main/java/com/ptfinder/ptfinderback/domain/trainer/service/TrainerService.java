package com.ptfinder.ptfinderback.domain.trainer.service;

import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerCreateDto;
import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerDto;

public interface TrainerService {

    TrainerDto createTrainer(TrainerCreateDto trainerCreateDto);
}
