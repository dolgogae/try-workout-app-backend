package com.tryworkout.backend.domain.trainer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TrainerType {

    HEALTH("HEALTH", "헬스 트레이너"),
    PILATES("PILATES", "필라테스");

    private final String key;
    private final String title;
}
