package com.example.test.dto;

public class ReviewUpdateRequest {

    private String content;
    private Integer rating;

    public ReviewUpdateRequest() {
    }

    public String getContent() {
        return content;
    }

    public Integer getRating() {
        return rating;
    }
}