package com.muah.muahbackend.domain.store.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUploadRequest {

    private Long WriterId;
    private Long ProductId;
    private String Content;
}
