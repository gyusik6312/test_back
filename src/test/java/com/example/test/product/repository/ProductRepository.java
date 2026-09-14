package com.example.test.product.repository;

import com.example.test.product.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 로그인한 사용자가 등록한 상품 조회.
    List<Product> findByUserId(Long userId);

    // 상품명에 검색어가 포함된 상품 조회. 영문 대소문자를 구분하지 않는다.
    List<Product> findByNameContainingIgnoreCase(String keyword, Sort sort);
}
