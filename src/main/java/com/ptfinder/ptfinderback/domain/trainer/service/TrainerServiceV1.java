package com.ptfinder.ptfinderback.domain.trainer.service;

import com.ptfinder.ptfinderback.domain.trainer.data.Trainer;
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
    private final ModelMapper mapper;

    @Override
    @Transactional
    public TrainerDto createTrainer(TrainerDto trainerDto) {
        Trainer trainer = Trainer.create(trainerDto);
        UserEntity userEntity = userJpaRepository.findById(trainerDto.getUserId()).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));
        trainer.updateUser(userEntity);

        Trainer savedTrainer = trainerJpaRepository.save(trainer);

        TrainerDto result = mapper.map(savedTrainer, TrainerDto.class);
        result.setUserId(savedTrainer.getUser().getId());
        // TODO: GYM 쪽 로직 생성한 뒤에 정보 넣어줘야함 현재는 null 값 들어가는 중

        return result;
    }

    @Override
    public TrainerDto updateTrainerDiscountRate(Long trainerId, Float discountRate) {
        Trainer trainer = trainerJpaRepository.findById(trainerId).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));
        trainer.updateDiscountRate(discountRate);

        TrainerDto result = mapper.map(trainer, TrainerDto.class);
        log.info("updated trainer = {}", result.toString());

        return result;
    }
}
