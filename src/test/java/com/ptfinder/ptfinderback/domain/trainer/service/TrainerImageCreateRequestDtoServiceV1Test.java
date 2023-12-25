package com.ptfinder.ptfinderback.domain.trainer.service;

import com.tryworkout.backend.domain.trainer.dto.TrainerCreateDto;
import com.tryworkout.backend.domain.trainer.dto.TrainerDto;
import com.tryworkout.backend.domain.trainer.repository.TrainerJpaRepository;
import com.tryworkout.backend.domain.trainer.service.TrainerService;
import com.tryworkout.backend.domain.user.data.UserEntity;
import com.tryworkout.backend.domain.user.repository.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TrainerImageCreateRequestDtoServiceV1Test {

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