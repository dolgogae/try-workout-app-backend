package com.tryworkout.backend.domain.gym.service.feestrategy;

import com.tryworkout.backend.domain.gym.data.Gym;
import com.tryworkout.backend.domain.gym.dto.GymFeeUpdateDto;

public class LockerFeeUpdateStrategy implements GymFeeUpdateStrategy {
    @Override
    public void updateFee(Gym gym, GymFeeUpdateDto dto) {
        if (dto.getLockerYn()) {
            // lockerFee 업데이트 로직
        }
    }
}

