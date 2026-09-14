package com.example.test.product.service;

import com.example.test.product.dto.ProductCreateRequest;
import com.example.test.product.entity.Product;
import com.example.test.product.repository.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * 상품 등록 처리 담당. 인증 정보 추출은 호출하는 쪽에서 처리한다.
 * TODO: Controller에서 기존 JWT 인증 정보의 사용자 ID를 전달하도록 연결.
 * 현재 userId 검증은 필수값·양수 확인이며, JWT 검증이나 사용자 존재 확인이 아니다.
 * TODO: 선택 사진 업로드와 상품 이미지 저장, 사진 미등록 시 기본 이미지 응답 연결.
 */
@Service
@Validated
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 상품 정보를 저장하고 생성된 상품 ID를 반환한다.
     * userId는 요청 본문이 아닌, 검증된 인증 정보에서 전달해야 한다.
     */
    @Transactional
    public Long createProduct(@NotNull @Valid ProductCreateRequest request,
                              @NotNull @Positive Long userId) {
        Product product = Product.create(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStock(),
                userId
        );

        Product savedProduct = productRepository.save(product);
        return savedProduct.getProductId();
    }
}
