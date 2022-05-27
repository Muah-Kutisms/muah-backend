package com.muah.muahbackend.domain.estimate.dto;

import com.muah.muahbackend.domain.estimate.entity.Proposal;
import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.estimate.entity.SheetFuneral;
import com.muah.muahbackend.domain.estimate.entity.SheetStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
public class SheetForFuneralDto {

    private Long id;
    private String petName;
    private String question;
    private String way;
    private String service;
    private String location;
    private LocalDate funeralDate;
    private String option;
    private SheetStatus status;
    private List<Proposal> proposals;
    private List<SheetFuneral> funerals;
    private LocalDateTime createdDate;


    public SheetForFuneralDto(Sheet sheet){
        this.id = sheet.getId();
        this.petName = sheet.getPetName();
        this.question = sheet.getQuestion();
        this.way = sheet.getWay();
        this.service = sheet.getService();
        this.location = sheet.getLocation();
        this.funeralDate = sheet.getFuneralDate();
        this.option = sheet.getOption();
        this.status = sheet.getStatus();
        this.proposals = sheet.getProposals();
        this.funerals = sheet.getFunerals();
        this.createdDate = sheet.getCreatedAt();
    }


}
