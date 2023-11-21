package com.ptfinder.ptfinderback.domain.review.repository;

import com.ptfinder.ptfinderback.domain.review.data.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
}
