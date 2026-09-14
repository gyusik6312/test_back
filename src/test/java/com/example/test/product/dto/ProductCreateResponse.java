package com.example.test.product.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 상품 등록 성공 시 생성된 상품 ID를 전달하는 응답.
 * TODO: 팀의 공통 응답 형식 확정 후 반영하고 Controller에서 반환하도록 연결.
 */
@Getter
@RequiredArgsConstructor
public class ProductCreateResponse {

    private final Long productId;
}
