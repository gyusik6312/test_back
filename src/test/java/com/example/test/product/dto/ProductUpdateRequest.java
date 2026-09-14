package com.example.test.product.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 상품 부분 수정 요청. 생략하거나 null인 필드는 기존 값을 유지한다.
 * 설명을 지우려면 빈 문자열을 전달한다. 빈 요청은 변경 없이 처리한다.
 * TODO: 팀 상세 명세에서 null 및 빈 요청 처리 규칙 확정.
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductUpdateRequest {

    @Size(max = 100, message = "상품명은 100자 이하여야 합니다.")
    @Pattern(regexp = "(?s).*[^\\p{javaWhitespace}].*", message = "상품명은 공백일 수 없습니다.")
    private String name;

    private String description;

    @PositiveOrZero(message = "가격은 0 이상이어야 합니다.")
    private Integer price;

    @PositiveOrZero(message = "재고는 0 이상이어야 합니다.")
    private Integer stock;
}
