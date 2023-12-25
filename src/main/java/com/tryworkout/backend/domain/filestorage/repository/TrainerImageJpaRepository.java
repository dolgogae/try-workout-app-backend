package com.tryworkout.backend.domain.filestorage.repository;

import com.tryworkout.backend.domain.filestorage.data.TrainerImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerImageJpaRepository extends JpaRepository<TrainerImage, Long> {
}
