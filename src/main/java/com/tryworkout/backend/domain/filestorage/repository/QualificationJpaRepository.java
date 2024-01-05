package com.tryworkout.backend.domain.filestorage.repository;

import com.tryworkout.backend.domain.filestorage.data.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationJpaRepository extends JpaRepository<Qualification, Long> {
}
