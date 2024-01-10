package com.tryworkout.backend.domain.review.service;

import com.tryworkout.backend.domain.review.dto.ReviewCreateDto;
import com.tryworkout.backend.domain.review.dto.ReviewDto;

public interface ReviewService {
    ReviewDto createReview(ReviewCreateDto createDto);
    void deleteReview(Long reviewId);
}
