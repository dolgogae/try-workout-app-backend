package com.ptfinder.ptfinderback.domain.trainer.service;

import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerCreateDto;
import com.ptfinder.ptfinderback.domain.trainer.dto.TrainerDto;
import com.ptfinder.ptfinderback.domain.trainer.repository.TrainerJpaRepository;
import com.ptfinder.ptfinderback.domain.user.data.UserEntity;
import com.ptfinder.ptfinderback.domain.user.repository.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TrainerServiceV1Test {

    @Autowired
    TrainerService trainerService;
    @Autowired
    TrainerJpaRepository trainerJpaRepository;
    @Autowired
    UserJpaRepository userJpaRepository;
    UserEntity user;

    @BeforeEach
    void init(){
        user = userJpaRepository.findById(1L).get();

    }

    @Test
    void createTrainerTest(){
        // given
        TrainerCreateDto trainerDto = TrainerCreateDto.builder()
                .userId(1L)
                .introduction("안녕하세요")
                .build();

        // when
        TrainerDto result = trainerService.createTrainer(trainerDto);

        // then
        assertThat(result.getUserId()).isEqualTo(1L);
    }
}