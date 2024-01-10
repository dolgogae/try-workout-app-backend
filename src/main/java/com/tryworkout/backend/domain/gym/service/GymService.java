package com.tryworkout.backend.domain.gym.service;

import com.tryworkout.backend.domain.gym.dto.*;

import java.util.List;

public interface GymService {

    GymDto getGym(GymCreateDto gymCreateDto);
    GymDto updateGymFees(GymFeeUpdateDto gymFeeUpdateDto);

    GymDto updateGymOperatingTime(GymOperatingTimeDto gymOperatingTimeDto);

    GymDto updateGymInformation(GymInfoUpdateDto gymInfoUpdateDto);

    List<GymLocationDto> getKNearestLocations(Integer k, GymLocationDto gymLocation);

    Boolean isGymUpdateAuth(Long trainerId, Long gymId);
}
