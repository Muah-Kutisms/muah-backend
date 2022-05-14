package com.muah.muahbackend.domain.store.dto;

import com.muah.muahbackend.domain.store.entity.Review;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private String writerName;
    private String content;
    private ProductMenuDto product;

    public ReviewDto(Review review){
        this.id = review.getId();
        this.writerName = review.getWriter().getName();
        this.content = review.getContent();
        this.product = new ProductMenuDto(review.getProduct());
    }
}
