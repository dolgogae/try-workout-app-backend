package com.tryworkout.backend.domain.gym.repository;

import com.tryworkout.backend.domain.gym.data.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymJpaRepository extends JpaRepository<Gym, Long> {
}
