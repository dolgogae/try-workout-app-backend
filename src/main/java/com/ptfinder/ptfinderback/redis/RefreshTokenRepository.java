package com.ptfinder.ptfinderback.redis;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByAuthId(String authId);
}