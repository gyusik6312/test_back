package com.example.test.product.exception;

/**
 * 상품 등록자와 작업을 요청한 사용자가 다를 때 발생한다.
 * TODO: 상품 API 예외 처리에서 HTTP 403 및 팀 공통 오류 응답으로 연결.
 */
public class ProductAccessDeniedException extends RuntimeException {

    public ProductAccessDeniedException() {
        super("상품 등록자만 해당 작업을 수행할 수 있습니다.");
    }
}
