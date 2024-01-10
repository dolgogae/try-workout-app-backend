package com.tryworkout.backend.domain.gym.service.feestrategy;

import com.tryworkout.backend.domain.gym.data.Gym;
import com.tryworkout.backend.domain.gym.dto.GymFeeUpdateDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GymFeeUpdater {
    private List<GymFeeUpdateStrategy> strategies;

    public GymFeeUpdater() {
        strategies = new ArrayList<>();
        strategies.add(new ParkingFeeUpdateStrategy());
        strategies.add(new WearFeeUpdateStrategy());
        strategies.add(new LockerFeeUpdateStrategy());
    }

    public Gym updateFees(Gym gym, GymFeeUpdateDto dto) {
        for (GymFeeUpdateStrategy strategy : strategies) {
            strategy.updateFee(gym, dto);
        }
        return gym;
    }
}

