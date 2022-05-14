package com.muah.muahbackend.domain.store.dto;

import com.muah.muahbackend.domain.store.entity.Review;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductReviewDto {
    private Long id;
    private String writerName;
    private String content;

    public ProductReviewDto(Review review){
        this.id = review.getId();
        this.writerName = review.getWriter().getName();
        this.content = review.getContent();
    }
}
