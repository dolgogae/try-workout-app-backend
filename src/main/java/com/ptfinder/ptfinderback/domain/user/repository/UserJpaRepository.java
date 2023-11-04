package com.ptfinder.ptfinderback.domain.user.repository;

import com.ptfinder.ptfinderback.domain.user.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
