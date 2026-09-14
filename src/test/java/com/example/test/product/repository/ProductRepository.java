package com.example.test.product.repository;

import com.example.test.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 로그인한 사용자가 등록한 상품 조회.
    List<Product> findByUserId(Long userId);
}
