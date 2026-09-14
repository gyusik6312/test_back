package com.example.test.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 사용자 엔티티와 인증 구현이 확정되기 전에는 ID로 보관한다.
    @Column(name = "user_id")
    private Long userId;

    /**
     * 검증된 상품 정보로 새 상품을 만든다. userId는 인증 정보에서 가져온다.
     * 상품 ID와 생성·수정 시간은 DB 저장 시 설정된다.
     */
    public static Product create(String name, String description, Integer price,
                                 Integer stock, Long userId) {
        Product product = new Product();
        product.name = name;
        product.description = description;
        product.price = price;
        product.stock = stock;
        product.userId = userId;
        return product;
    }

    /** 검증된 수정 값만 반영한다. null은 기존 값 유지이며 등록자와 ID는 변경하지 않는다. */
    public void update(String name, String description, Integer price, Integer stock) {
        if (name != null) {
            this.name = name;
        }
        if (description != null) {
            this.description = description;
        }
        if (price != null) {
            this.price = price;
        }
        if (stock != null) {
            this.stock = stock;
        }
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
