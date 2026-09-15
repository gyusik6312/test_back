package com.example.test.service;

import com.example.test.dto.ReviewCreateRequest;
import com.example.test.dto.ReviewUpdateRequest;
import com.example.test.entity.Review;
import com.example.test.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // 후기 추가
    public Review createReview(Long productId, ReviewCreateRequest request) {

        Review review = new Review(
                productId,
                request.getUserId(),
                request.getContent(),
                request.getRating()
        );

        return reviewRepository.save(review);
    }

    // 후기 조회
    public List<Review> getReviews(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    // 후기 수정
    public Review updateReview(Long reviewId, ReviewUpdateRequest request) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("후기를 찾을 수 없습니다."));

        review.update(
                request.getContent(),
                request.getRating()
        );

        return reviewRepository.save(review);
    }
}