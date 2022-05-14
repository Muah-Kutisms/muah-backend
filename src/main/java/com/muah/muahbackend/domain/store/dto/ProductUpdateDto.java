package com.muah.muahbackend.domain.store.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductUpdateDto {

    private String name;
    private Integer price;
    private String option;
    private String description;
}
