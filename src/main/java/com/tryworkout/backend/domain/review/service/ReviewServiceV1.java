package com.tryworkout.backend.domain.review.service;

import com.tryworkout.backend.domain.member.data.Member;
import com.tryworkout.backend.domain.member.repository.MemberJpaRepository;
import com.tryworkout.backend.domain.review.data.Review;
import com.tryworkout.backend.domain.review.dto.ReviewCreateDto;
import com.tryworkout.backend.domain.review.dto.ReviewDto;
import com.tryworkout.backend.domain.review.repository.ReviewJpaRepository;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import com.tryworkout.backend.domain.trainer.repository.TrainerJpaRepository;
import com.tryworkout.backend.global.error.ErrorCode;
import com.tryworkout.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceV1 implements ReviewService{

    private final ReviewJpaRepository reviewJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final TrainerJpaRepository trainerJpaRepository;
    private final ModelMapper mapper;

    @Override
    public ReviewDto createReview(ReviewCreateDto createDto) {
        Member member = memberJpaRepository.findById(createDto.getMemberId()).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));
        Trainer trainer = trainerJpaRepository.findById(createDto.getTrainerId()).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_EXIST));

        Review review = Review.create(createDto, member, trainer);

        Review savedReview = reviewJpaRepository.save(review);
        ReviewDto result = makeReviewDto(savedReview);

        return result;
    }

    private ReviewDto makeReviewDto(Review savedReview) {
        ReviewDto result = mapper.map(savedReview, ReviewDto.class);
        result.setMemberId(savedReview.getMember().getId());
        result.setTrainerId(savedReview.getTrainer().getId());
        return result;
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewJpaRepository.deleteById(reviewId);
    }
}
