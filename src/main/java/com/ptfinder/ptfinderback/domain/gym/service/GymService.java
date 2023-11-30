package com.ptfinder.ptfinderback.domain.gym.service;

import com.ptfinder.ptfinderback.domain.gym.dto.GymCreateDto;
import com.ptfinder.ptfinderback.domain.gym.dto.GymDto;
import com.ptfinder.ptfinderback.domain.gym.dto.GymLocationDto;

import java.util.List;

public interface GymService {

    GymDto getGym(GymCreateDto gymCreateDto);
    List<GymLocationDto> getKNearestLocations(Integer k, GymLocationDto gymLocation);
}
