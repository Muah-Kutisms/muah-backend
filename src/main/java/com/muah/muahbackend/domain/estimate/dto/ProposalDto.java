package com.muah.muahbackend.domain.estimate.dto;

import com.muah.muahbackend.domain.estimate.entity.Proposal;
import com.muah.muahbackend.domain.estimate.entity.ProposalStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProposalDto {

    private Long id;
    private Long sheetId;
    private Long writerId;
    private String FuneralName;
    private String content;
    private Integer price;
    private LocalDateTime createdDate;
    private ProposalStatus status;

    public ProposalDto(Proposal proposal){
        this.id = proposal.getId();
        this.sheetId = proposal.getSheet().getId();
        this.writerId = proposal.getWriter().getId();
        this.FuneralName = proposal.getWriter().getFuneralName();
        this.content = proposal.getContent();
        this.price = proposal.getPrice();
        this.createdDate = proposal.getCreatedAt();
        this.status = proposal.getStatus();

    }
}
