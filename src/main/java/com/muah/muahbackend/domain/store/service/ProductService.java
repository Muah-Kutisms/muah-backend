package com.muah.muahbackend.domain.store.service;

import com.muah.muahbackend.domain.store.dto.*;
import com.muah.muahbackend.domain.store.entity.Product;
import com.muah.muahbackend.domain.store.entity.Review;
import com.muah.muahbackend.domain.store.repository.ProductRepository;
import com.muah.muahbackend.domain.store.repository.ReviewRepository;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.ProductNotFoundException;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ProductUploadResponse uploadProduct(ProductUploadRequest request){
        User seller = userRepository.findById(request.getSellerId()).orElseThrow(() -> new UserNotFoundException());
        Product product = Product.builder()
                .seller(seller)
                .description(request.getDescription())
                .name(request.getName())
                .option(request.getOption())
                .price(request.getPrice())
                .build();
        productRepository.save(product);

        return new ProductUploadResponse(product.getId());
    }

    @Transactional(readOnly = true)
    public List<ProductMenuDto> getProductMenu() {
        List<Product> products= productRepository.findAll(Sort.by("isAdvertised").descending().and(Sort.by("createdAt").descending()));
        ArrayList<ProductMenuDto> productMenu = products.stream().map(p-> new ProductMenuDto(p)).collect(toCollection(ArrayList::new));
        return productMenu;
    }

    @Transactional(readOnly = true)
    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
        ProductDto result = new ProductDto(product);
        setReviews(product, result);
        return result;
    }

    private void setReviews(Product product, ProductDto productDto){
        Collection<Review> reviewsData = reviewRepository.findAllByProduct(product);
        List<ProductReviewDto> reviews = reviewsData.stream().map(r -> new ProductReviewDto(r)).collect(toCollection(ArrayList::new));
        productDto.setReviews(reviews);
    }

    @Transactional
    public ProductDto updateProduct(Long id, ProductUpdateDto request) {
        return productRepository.findById(id)
                .map(p -> {
                    p.setName(request.getName());
                    p.setDescription(request.getDescription());
                    p.setPrice((request.getPrice()));
                    return new ProductDto(productRepository.save(p));
                })
                .orElseThrow(() -> {
                    throw new ProductNotFoundException();
                });
    }

    public boolean deleteProduct(Long id) throws IllegalAccessError{
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException());
        try {
            productRepository.delete((product));

        }catch (Exception e){
            return false;
        }
        return true;
    }


}

