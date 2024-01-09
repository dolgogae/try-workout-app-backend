package com.tryworkout.backend.domain.trainer.service;

import com.tryworkout.backend.domain.gym.data.Gym;
import com.tryworkout.backend.domain.gym.repository.GymJpaRepository;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import com.tryworkout.backend.domain.trainer.dto.TrainerCreateDto;
import com.tryworkout.backend.domain.trainer.dto.TrainerDto;
import com.tryworkout.backend.domain.trainer.repository.TrainerJpaRepository;
import com.tryworkout.backend.domain.user.data.UserEntity;
import com.tryworkout.backend.domain.user.repository.UserJpaRepository;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
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
        /**
         * Gym is not essential parameter in register.
         * so, if trainer does not necessary enter the gym info when they are registered
         * Trainer domain's gym put null
         */
        Gym gym = null;
        if(trainerCreateDto.getGymId() != null) {
            gym = gymJpaRepository.findById(trainerCreateDto.getGymId()).orElseThrow(() ->
                    new BusinessException(ErrorCode.GYM_NOT_FOUND));
        }
        UserEntity userEntity = userJpaRepository.findById(trainerCreateDto.getUserId()).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        Trainer trainer = Trainer.create(trainerCreateDto, userEntity, gym);
        Trainer savedTrainer = trainerJpaRepository.save(trainer);

        TrainerDto result = makeTrainerDto(savedTrainer);

        return result;
    }

    @Override
    public TrainerDto updateGym(Long trainerId, Long gymId) {
        Trainer findTrainer = trainerJpaRepository.findById(trainerId).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));
        Gym gym = gymJpaRepository.findById(gymId).orElseThrow(() ->
                new BusinessException(ErrorCode.GYM_NOT_FOUND));

        Trainer trainer = findTrainer.registerGym(gym);
        log.info("trainer id = {}", trainer.getId());
        Trainer updatedTrainer = trainerJpaRepository.save(trainer);

        TrainerDto result = mapper.map(updatedTrainer, TrainerDto.class);
        result.setGymId(gymId);

        return result;
    }

    private TrainerDto makeTrainerDto(Trainer savedTrainer) {
        TrainerDto result = mapper.map(savedTrainer, TrainerDto.class);
        result.setUserId(savedTrainer.getUser().getId());
        if(savedTrainer.getGym() != null) {
            result.setGymId(savedTrainer.getGym().getId());
        }
        return result;
    }
}
