package com.example.test.product.dto;

import com.example.test.product.entity.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * 상품 목록의 개별 상품 정보. 검색 결과 및 내 상품 목록에도 재사용할 수 있다.
 * TODO: 팀의 상세 응답 명세 확인 및 대표 이미지·기본 이미지 URL 연결.
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductListResponse {

    private final Long productId;
    private final String name;
    private final Integer price;
    private final Integer stock;
    private final LocalDateTime createdAt;

    public static ProductListResponse from(Product product) {
        return new ProductListResponse(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCreatedAt()
        );
    }
}
