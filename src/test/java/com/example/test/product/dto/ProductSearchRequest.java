package com.example.test.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 상품명 부분 검색 조건.
 * TODO: 팀 명세에서 쿼리 파라미터 이름 및 설명 포함 검색 여부 확정.
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductSearchRequest {

    @NotBlank(message = "검색어는 필수입니다.")
    private String keyword;
}
