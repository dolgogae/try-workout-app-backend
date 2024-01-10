package com.tryworkout.backend.domain.gym.service.feestrategy;

import com.tryworkout.backend.domain.gym.data.Gym;
import com.tryworkout.backend.domain.gym.dto.GymFeeUpdateDto;

public interface GymFeeUpdateStrategy {
    void updateFee(Gym gym, GymFeeUpdateDto dto);
}
