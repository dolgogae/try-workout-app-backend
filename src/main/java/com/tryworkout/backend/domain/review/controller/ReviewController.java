package com.tryworkout.backend.domain.review.controller;

import com.tryworkout.backend.domain.review.dto.ReviewCreateDto;
import com.tryworkout.backend.domain.review.dto.ReviewDto;
import com.tryworkout.backend.domain.review.service.ReviewService;
import com.tryworkout.backend.global.result.ResultCode;
import com.tryworkout.backend.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    public ResponseEntity<ResultResponse> createReview(
            @RequestBody ReviewCreateDto reviewCreateDto
    ){
        ReviewDto reviewDto = reviewService.createReview(reviewCreateDto);

        ResultResponse result = ResultResponse.of(ResultCode.REVIEW_CREATE_SUCCESS, reviewDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{reviewId}")
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    public ResponseEntity<ResultResponse> deleteReview(
            @PathVariable Long reviewId
    ){
        reviewService.deleteReview(reviewId);

        ResultResponse result = ResultResponse.of(ResultCode.REVIEW_DELETE_SUCCESS, "delete review " + reviewId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
