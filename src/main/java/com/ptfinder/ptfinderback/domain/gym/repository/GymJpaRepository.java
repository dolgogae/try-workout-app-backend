package com.ptfinder.ptfinderback.domain.gym.repository;

import com.ptfinder.ptfinderback.domain.gym.data.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymJpaRepository extends JpaRepository<Gym, Long> {
}
