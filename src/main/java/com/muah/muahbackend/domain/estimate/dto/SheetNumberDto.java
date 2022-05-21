package com.muah.muahbackend.domain.estimate.dto;


import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.estimate.entity.SheetFuneral;
import com.muah.muahbackend.domain.estimate.entity.SheetStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
public class SheetNumberDto {
    private Long id;
    private Long petId;
    private int sheetNumberId;
    private String question;
    private String way;
    private String service;
    private String location;
    private LocalDate funeralDate;
    private String option;
    private SheetStatus status;
    private List<SheetFuneral> funerals;


    public SheetNumberDto(Sheet sheet, int sheetNumberId){
        this.id = sheet.getId();
        this.petId = sheet.getPet().getId();
        this.sheetNumberId = sheetNumberId;
        this.question = sheet.getQuestion();
        this.way = sheet.getWay();
        this.service = sheet.getService();
        this.location = sheet.getLocation();
        this.funeralDate = sheet.getFuneralDate();
        this.option = sheet.getOption();
        this.status = sheet.getStatus();
        this.funerals = sheet.getFunerals();

    }


}
