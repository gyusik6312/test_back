package com.example.test.product.dto;

import com.example.test.product.entity.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * 상품 상세 응답.
 * TODO: 팀의 상세 응답 명세 확인 및 상품 이미지·기본 이미지 URL 연결.
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDetailResponse {

    private final Long productId;
    private final String name;
    private final String description;
    private final Integer price;
    private final Integer stock;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static ProductDetailResponse from(Product product) {
        return new ProductDetailResponse(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
