package com.muah.muahbackend.domain.store.repository;

import com.muah.muahbackend.domain.store.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
