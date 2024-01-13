package com.tryworkout.backend.domain.gym.service.feestrategy;

import com.tryworkout.backend.domain.gym.data.Gym;
import com.tryworkout.backend.domain.gym.dto.GymFeeUpdateDto;

/**
 * 복잡도 고민으로 아직 추가하지 않은 strategy pattern
 * 고민좀 해보고 추가해야할듯
 * 지금은 그냥 덮어써버리는게 훨씬 효율적이고 이득인 것처럼 보임
 * 계산로직이 들어가기 시작하면 해당 패턴을 도입하는게 좋을 것으로 보임
 */
public interface GymFeeUpdateStrategy {
    void updateFee(Gym gym, GymFeeUpdateDto dto);
}
