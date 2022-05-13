package com.muah.muahbackend.domain.store.dto;

import com.muah.muahbackend.domain.store.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto {

    private String sellerName;
    private String name;
    private LocalDate createdAt;
    private Integer price;
    private String option;
    private String description;
    private List<ProductReviewDto> reviews;

    public ProductDto(Product product) {
        this.sellerName = product.getSeller().getName();
        this.name = product.getName();
        this.createdAt = product.getCreatedAt().toLocalDate();
        this.price = product.getPrice();
        this.option = product.getOption();
        this.description = product.getDescription();
    }

}
