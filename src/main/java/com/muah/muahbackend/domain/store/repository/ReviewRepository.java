package com.muah.muahbackend.domain.store.repository;

import com.muah.muahbackend.domain.store.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
