package com.muah.muahbackend.domain.estimate.dto;


import com.muah.muahbackend.domain.user.entity.User;
import lombok.*;

@Data
@NoArgsConstructor
public class ProposalUploadRequest {

    private Long sheetId;
    private Long writerId;
    private String content;
    private Integer price;
}
