package com.tryworkout.backend.domain.review.repository;

import com.tryworkout.backend.domain.review.data.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
}
