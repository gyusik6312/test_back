package com.example.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Long productId;
    private Long userId;
    private String content;
    private Integer rating;
    private String imageUrl;

    public Review() {
    }

    public Review(Long productId, Long userId, String content, Integer rating) {
        this.productId = productId;
        this.userId = userId;
        this.content = content;
        this.rating = rating;
    }

    public void update(String content, Integer rating) {
        this.content = content;
        this.rating = rating;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public Integer getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}