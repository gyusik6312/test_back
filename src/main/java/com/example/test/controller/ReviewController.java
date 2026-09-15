package com.example.test.controller;

import com.example.test.dto.ReviewCreateRequest;
import com.example.test.dto.ReviewUpdateRequest;
import com.example.test.entity.Review;
import com.example.test.service.ReviewService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 후기 추가
    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<Review> createReview(
            @PathVariable Long productId,
            @RequestBody ReviewCreateRequest request) {

        Review review = reviewService.createReview(productId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(review);
    }

    // 후기 조회
    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<List<Review>> getReviews(
            @PathVariable Long productId) {

        List<Review> reviews = reviewService.getReviews(productId);

        return ResponseEntity.ok(reviews);
    }

    // 후기 수정
    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewUpdateRequest request) {

        Review review = reviewService.updateReview(reviewId, request);

        return ResponseEntity.ok(review);
    }
}