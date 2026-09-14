package com.example.test.product.exception;

/**
 * 요청한 ID의 상품이 존재하지 않을 때 발생한다.
 * TODO: 상품 API 예외 처리에서 HTTP 404 및 팀 공통 오류 응답으로 연결.
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long productId) {
        super("상품을 찾을 수 없습니다. 상품 ID: " + productId);
    }
}
