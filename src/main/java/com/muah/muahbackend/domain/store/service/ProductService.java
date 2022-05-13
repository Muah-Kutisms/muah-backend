package com.muah.muahbackend.domain.store.service;

import com.muah.muahbackend.domain.store.dto.ProductDto;
import com.muah.muahbackend.domain.store.dto.ProductMenuDto;
import com.muah.muahbackend.domain.store.dto.ProductUploadRequest;
import com.muah.muahbackend.domain.store.dto.ProductUploadResponse;
import com.muah.muahbackend.domain.store.entity.Product;
import com.muah.muahbackend.domain.store.repository.ProductRepository;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.ProductNotFoundException;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

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
        return new ProductDto(product);
    }
}
