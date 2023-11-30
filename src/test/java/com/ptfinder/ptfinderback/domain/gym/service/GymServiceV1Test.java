package com.ptfinder.ptfinderback.domain.gym.service;

import com.ptfinder.ptfinderback.domain.gym.dto.GymCreateDto;
import com.ptfinder.ptfinderback.domain.gym.dto.GymDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class GymServiceV1Test {

    @Autowired GymService gymService;

    @Test
    @DisplayName("Gym 생성 서비스 테스트")
    public void getGymTest(){
        // given
        GymCreateDto gymCreateDto = GymCreateDto.builder()
                .gymName("테스트용 gym")
                .address("서울특별시 서초구 000동 123-123")
                .roadAddress("서울특별시 서초구 00로 123")
                .mapX(123.12F)
                .mapY(123.12F)
                .build();

        // when
        GymDto gym = gymService.getGym(gymCreateDto);

        // then
        assertThat(gym.getGymName()).isEqualTo(gymCreateDto.getGymName());
        assertThat(gym.getAddress()).isEqualTo(gymCreateDto.getAddress());
        assertThat(gym.getRoadAddress()).isEqualTo(gymCreateDto.getRoadAddress());
        assertThat(gym.getMapX()).isEqualTo(gymCreateDto.getMapX());
    }
}