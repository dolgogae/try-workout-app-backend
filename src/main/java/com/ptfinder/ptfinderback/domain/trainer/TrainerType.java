package com.ptfinder.ptfinderback.domain.trainer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TrainerType {

    GYM("GYM", "헬스 트레이너");

    private final String key;
    private final String title;
}
