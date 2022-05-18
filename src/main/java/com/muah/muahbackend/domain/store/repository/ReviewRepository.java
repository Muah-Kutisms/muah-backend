package com.muah.muahbackend.domain.store.repository;

import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.store.entity.Product;
import com.muah.muahbackend.domain.store.entity.Review;
import com.muah.muahbackend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.Collection;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("from Review r where r.product = :product")
    Collection<Review> findAllByProduct(@Param("product") Product product);

    @Query("from Review r where r.writer= :writer")
    Collection<Review> findAllByWriter(@Param("writer") User writer);
}
