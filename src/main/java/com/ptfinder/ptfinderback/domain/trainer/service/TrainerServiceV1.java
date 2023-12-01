package com.ptfinder.ptfinderback.domain.trainer.service;

import com.ptfinder.ptfinderback.domain.fee.dto.FeeCreateDto;
import com.ptfinder.ptfinderback.domain.gym.data.Gym;
import com.ptfinder.ptfinderback.domain.gym.repository.GymJpaRepository;
import com.ptfinder.ptfinderback.domain.gym.service.GymService;
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
    private final GymService gymService;
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
        Trainer savedTrainer = trainerJpaRepository.save(trainer);;

        TrainerDto result = makeTrainerDto(savedTrainer);

        return result;
    }

    @Override
    public TrainerDto updateGym(String email, Long gymId) {
        UserEntity userEntity = userJpaRepository.findTrainerUserByEmail(email).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));
        Gym gym = gymJpaRepository.findById(gymId).orElseThrow(() ->
                new BusinessException(ErrorCode.GYM_NOT_FOUND));

        Trainer trainer = userEntity.getTrainer().updateGym(gym);
        log.info("trainer id = {}", trainer.getId());
        Trainer updatedTrainer = trainerJpaRepository.save(trainer);

        TrainerDto result = mapper.map(updatedTrainer, TrainerDto.class);
        result.setGymId(gymId);

        return result;
    }

    /**
     * fee create 할때 생성 -> 따로 controller 만들 필요 없을 듯
     * @param trainerID
     * @param feeCreateDto
     * @return
     */
    @Override
    public TrainerDto updateFee(Long trainerID, FeeCreateDto feeCreateDto) {
        return null;
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
