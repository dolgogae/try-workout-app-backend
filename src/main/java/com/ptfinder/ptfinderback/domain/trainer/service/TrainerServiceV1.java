package com.ptfinder.ptfinderback.domain.trainer.service;

import com.ptfinder.ptfinderback.domain.gym.data.Gym;
import com.ptfinder.ptfinderback.domain.gym.repository.GymJpaRepository;
import com.ptfinder.ptfinderback.domain.trainer.data.Trainer;
import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerCreateDto;
import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerDto;
import com.ptfinder.ptfinderback.domain.trainer.repository.TrainerJpaRepository;
import com.ptfinder.ptfinderback.domain.user.data.UserEntity;
import com.ptfinder.ptfinderback.domain.user.repository.UserJpaRepository;
import com.ptfinder.ptfinderback.global.error.ErrorCode;
import com.ptfinder.ptfinderback.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainerServiceV1 implements TrainerService{

    private final TrainerJpaRepository trainerJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final GymJpaRepository gymJpaRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public TrainerDto createTrainer(TrainerCreateDto trainerCreateDto) {
        Gym gym = gymJpaRepository.findById(trainerCreateDto.getGymId()).orElseThrow(() ->
                new BusinessException(ErrorCode.GYM_NOT_FOUND));
        UserEntity userEntity = userJpaRepository.findById(trainerCreateDto.getUserId()).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        Trainer trainer = Trainer.create(trainerCreateDto, userEntity, gym);
        Trainer savedTrainer = trainerJpaRepository.save(trainer);

        TrainerDto result = getTrainerDto(savedTrainer);

        return result;
    }

    private TrainerDto getTrainerDto(Trainer savedTrainer) {
        TrainerDto result = mapper.map(savedTrainer, TrainerDto.class);
        result.setUserId(savedTrainer.getUser().getId());
        result.setGymId(savedTrainer.getGym().getId());
        return result;
    }
}
