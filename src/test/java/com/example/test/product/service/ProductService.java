package com.example.test.product.service;

import com.example.test.product.dto.ProductCreateRequest;
import com.example.test.product.dto.ProductDetailResponse;
import com.example.test.product.dto.ProductListResponse;
import com.example.test.product.entity.Product;
import com.example.test.product.exception.ProductNotFoundException;
import com.example.test.product.repository.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 상품 등록 및 목록·상세 조회 처리 담당. 인증 정보 추출은 호출하는 쪽에서 처리한다.
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
     * 상품 ID로 한 건을 조회한다. 존재하지 않으면 ProductNotFoundException 발생.
     * TODO: 상세 조회 Controller와 HTTP 오류 응답 연결.
     */
    @Transactional(readOnly = true)
    public ProductDetailResponse getProduct(@NotNull @Positive Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        return ProductDetailResponse.from(product);
    }

    /**
     * 전체 상품을 등록 시간 내림차순으로 조회한다. 같은 시간이면 상품 ID 내림차순.
     * TODO: 목록 Controller 연결. 페이지네이션은 상세 명세 확정 후 적용.
     */
    @Transactional(readOnly = true)
    public List<ProductListResponse> getProducts() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt", "productId");
        return productRepository.findAll(sort).stream()
                .map(ProductListResponse::from)
                .toList();
    }

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
