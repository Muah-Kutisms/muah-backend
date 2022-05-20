package com.muah.muahbackend.domain.estimate.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProposalUpdateDto {

    private String content;
    private Integer price;

}
