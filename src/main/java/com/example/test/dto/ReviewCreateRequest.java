package com.example.test.dto;

public class ReviewCreateRequest {

    private Long userId;
    private String content;
    private Integer rating;

    public ReviewCreateRequest() {
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
}