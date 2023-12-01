package com.ptfinder.ptfinderback.domain.user.repository;

import com.ptfinder.ptfinderback.domain.user.AccountType;
import com.ptfinder.ptfinderback.domain.user.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByEmailAndAccountType(String email, AccountType accountType);
    Optional<UserEntity> findById(Long id);
    @Query("SELECT u FROM UserEntity u " +
            "JOIN FETCH u.trainer t " +
            "WHERE u.email = :email")
    Optional<UserEntity> findTrainerUserByEmail(String email);
}
