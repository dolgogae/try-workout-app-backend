package com.tryworkout.backend.domain.service;

import com.tryworkout.backend.domain.gym.dto.GymCreateDto;
import com.tryworkout.backend.domain.gym.dto.GymDto;
import com.tryworkout.backend.domain.gym.dto.GymLocationDto;
import com.tryworkout.backend.domain.gym.service.GymService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class GymServiceV1Test {

    @Autowired
    GymService gymService;

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

    @Test
    @DisplayName("현재 중심에서 가장 가까운 gym 찾기 테스트")
    public void getKNearestLocations(){
        // given
        GymCreateDto origin = GymCreateDto.builder()
                .gymName("테스트용 gym")
                .address("서울특별시 서초구 000동 123-123")
                .roadAddress("서울특별시 서초구 00로 123")
                .mapX(100.F)
                .mapY(100.F)
                .build();
        GymDto gym = gymService.getGym(origin);
        GymCreateDto gymCreateDto1 = GymCreateDto.builder()
                .gymName("테스트용 gym")
                .address("서울특별시 서초구 000동 123-123")
                .roadAddress("서울특별시 서초구 00로 123")
                .mapX(123.12F)
                .mapY(123.12F)
                .build();
        gymService.getGym(gymCreateDto1);
        GymCreateDto gymCreateDto2 = GymCreateDto.builder()
                .gymName("테스트용 gym")
                .address("서울특별시 서초구 000동 123-123")
                .roadAddress("서울특별시 서초구 00로 123")
                .mapX(143.121F)
                .mapY(100.12F)
                .build();
        gymService.getGym(gymCreateDto2);
        GymCreateDto gymCreateDto3 = GymCreateDto.builder()
                .gymName("테스트용 gym")
                .address("서울특별시 서초구 000동 123-123")
                .roadAddress("서울특별시 서초구 00로 123")
                .mapX(12.11F)
                .mapY(1000.12F)
                .build();
        gymService.getGym(gymCreateDto3);
        GymCreateDto gymCreateDto4 = GymCreateDto.builder()
                .gymName("테스트용 gym")
                .address("서울특별시 서초구 000동 123-123")
                .roadAddress("서울특별시 서초구 00로 123")
                .mapX(111.121F)
                .mapY(100.12F)
                .build();
        gymService.getGym(gymCreateDto4);
        GymCreateDto gymCreateDto5 = GymCreateDto.builder()
                .gymName("테스트용 gym")
                .address("서울특별시 서초구 000동 123-123")
                .roadAddress("서울특별시 서초구 00로 123")
                .mapX(1010.121F)
                .mapY(777.12F)
                .build();
        gymService.getGym(gymCreateDto5);
        // when

        GymLocationDto gymLocationDto = GymLocationDto.builder().gymName(gym.getGymName()).mapX(gym.getMapX()).mapY(gym.getMapY()).build();
        List<GymLocationDto> kNearestLocations = gymService.getKNearestLocations(3, gymLocationDto);

        // then
        assertThat(kNearestLocations.size()).isEqualTo(3);

    }
}