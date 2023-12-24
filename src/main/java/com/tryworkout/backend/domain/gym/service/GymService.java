package com.tryworkout.backend.domain.gym.service;

import com.tryworkout.backend.domain.gym.dto.GymCreateDto;
import com.tryworkout.backend.domain.gym.dto.GymDto;
import com.tryworkout.backend.domain.gym.dto.GymLocationDto;

import java.util.List;

public interface GymService {

    GymDto getGym(GymCreateDto gymCreateDto);
    List<GymLocationDto> getKNearestLocations(Integer k, GymLocationDto gymLocation);
}
