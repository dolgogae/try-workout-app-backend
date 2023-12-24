package com.tryworkout.backend.domain.trainer.repository;

import com.tryworkout.backend.domain.trainer.data.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerJpaRepository extends JpaRepository<Trainer, Long> {
}
