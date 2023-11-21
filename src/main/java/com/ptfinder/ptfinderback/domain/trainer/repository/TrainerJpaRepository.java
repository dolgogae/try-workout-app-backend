package com.ptfinder.ptfinderback.domain.trainer.repository;

import com.ptfinder.ptfinderback.domain.trainer.data.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerJpaRepository extends JpaRepository<Trainer, Long> {
}
