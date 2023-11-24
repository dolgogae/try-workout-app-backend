package com.ptfinder.ptfinderback.domain.trainer.service;

import com.ptfinder.ptfinderback.domain.trainer.data.Trainer;
import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerDto;
import com.ptfinder.ptfinderback.domain.trainer.repository.TrainerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainerServiceV1 implements TrainerService{

    private final TrainerJpaRepository trainerJpaRepository;
    private final ModelMapper mapper;

    @Override
    public TrainerDto createTrainer(TrainerDto trainerDto) {
        Trainer trainer = Trainer.create(trainerDto);
        Trainer savedTrainer = trainerJpaRepository.save(trainer);

        TrainerDto result = mapper.map(savedTrainer, TrainerDto.class);
        log.info("create trainer = {}", result.toString());

        return result;
    }
}
