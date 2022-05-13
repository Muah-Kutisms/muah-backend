package com.muah.muahbackend.domain.store.dto;

import com.muah.muahbackend.domain.store.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProductMenuDto {

    private String sellerName;
    private String name;
    private LocalDate createdAt;
    private Integer price;

    public ProductMenuDto(Product product){
        this.sellerName = product.getSeller().getName();
        this.name = product.getName();
        this.price = product.getPrice();
        this.createdAt = product.getCreatedAt().toLocalDate();
    }
}
