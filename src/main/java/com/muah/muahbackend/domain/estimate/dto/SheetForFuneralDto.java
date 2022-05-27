package com.muah.muahbackend.domain.estimate.dto;

import com.muah.muahbackend.domain.estimate.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
public class SheetForFuneralDto {

    private Long id;
    private Long petId;
    private String petName;
    private String question;
    private String way;
    private String service;
    private String location;
    private LocalDate funeralDate;
    private String option;
    private SheetStatus status;
    private LocalDateTime createdDate;
    private Long proposalId;
    private String content;
    private Integer price;
    private ProposalStatus pstatus;


    public SheetForFuneralDto(Sheet sheet, Proposal proposal){

        this.id = sheet.getId();
        this.petId = sheet.getPet().getId();
        this.petName = sheet.getPetName();
        this.question = sheet.getQuestion();
        this.way = sheet.getWay();
        this.service = sheet.getService();
        this.location = sheet.getLocation();
        this.funeralDate = sheet.getFuneralDate();
        this.option = sheet.getOption();
        this.status = sheet.getStatus();
        this.createdDate = sheet.getCreatedAt();
        this.proposalId = proposal.getId();
        this.content = proposal.getContent();
        this.price = proposal.getPrice();
        this.pstatus = proposal.getStatus();

    }




}
