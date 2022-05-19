package com.muah.muahbackend.domain.store.service;

import com.muah.muahbackend.domain.store.dto.*;
import com.muah.muahbackend.domain.store.entity.Product;
import com.muah.muahbackend.domain.store.entity.Review;
import com.muah.muahbackend.domain.store.repository.ProductRepository;
import com.muah.muahbackend.domain.store.repository.ReviewRepository;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.ProductNotFoundException;
import com.muah.muahbackend.global.error.exception.ReviewNotFoundException;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewUploadResponse uploadReview(ReviewUploadRequest request){
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new ProductNotFoundException());
        User writer = userRepository.findById(request.getWriterId()).orElseThrow(() -> new UserNotFoundException());

        Review review = Review.builder()
                .writer(writer)
                .product(product)
                .content(request.getContent())
                .build();
        return new ReviewUploadResponse(reviewRepository.save(review).getId());
    }

    @Transactional(readOnly = true)
    public List<ReviewDto> getReviews() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        Collection<Review> reviewsData = reviewRepository.findAllByWriter(user);
        List<ReviewDto> reviews = reviewsData.stream().map(r -> new ReviewDto(r)).collect(toCollection(ArrayList::new));
        return reviews;
    }

    public boolean deleteReview(Long id) throws IllegalAccessError{
        Review review = reviewRepository.findById(id).orElseThrow(()-> new ReviewNotFoundException());
        try {
            reviewRepository.delete((review));
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
