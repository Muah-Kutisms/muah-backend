package com.muah.muahbackend.domain.estimate.dto;


import com.muah.muahbackend.domain.estimate.entity.ProposalStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProposalUpdateStatusDto {
    private ProposalStatus status;
}
