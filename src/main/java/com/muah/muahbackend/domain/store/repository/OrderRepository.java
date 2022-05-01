package com.muah.muahbackend.domain.store.repository;

import com.muah.muahbackend.domain.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
