package com.tryworkout.backend.domain.fee.repository;

import com.tryworkout.backend.domain.fee.data.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeJpaRepository extends JpaRepository<Fee, Long> {
}
