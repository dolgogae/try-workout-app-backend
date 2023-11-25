package com.ptfinder.ptfinderback.domain.fee.repository;

import com.ptfinder.ptfinderback.domain.fee.data.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeJpaRepository extends JpaRepository<Fee, Long> {
}
