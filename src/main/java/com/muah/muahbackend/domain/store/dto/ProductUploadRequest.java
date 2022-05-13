package com.muah.muahbackend.domain.store.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUploadRequest {

    private Long sellerId;
    private String name;
    private Integer price;
    private String option;
    private String description;

}
