package com.muah.muahbackend.domain.store.repository;

import com.muah.muahbackend.domain.store.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository <ProductImage, Long> {
}
