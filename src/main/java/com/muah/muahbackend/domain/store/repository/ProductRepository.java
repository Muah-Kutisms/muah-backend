package com.muah.muahbackend.domain.store.repository;

import com.muah.muahbackend.domain.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
