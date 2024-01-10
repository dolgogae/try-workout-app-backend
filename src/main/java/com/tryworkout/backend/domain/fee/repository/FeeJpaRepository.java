package com.tryworkout.backend.domain.fee.repository;

import com.tryworkout.backend.domain.fee.data.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeeJpaRepository extends JpaRepository<Fee, Long> {

    @Query("SELECT f from Fee f " +
            "where f.trainer.id = :trainerId")
    List<Fee> findByTrainer(Long trainerId);
}
