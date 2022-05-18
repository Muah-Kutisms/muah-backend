package com.muah.muahbackend.domain.estimate.dto;

import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.estimate.entity.SheetFuneral;
import com.muah.muahbackend.domain.estimate.entity.SheetStatus;
import com.muah.muahbackend.domain.pet.entity.Gender;
import com.muah.muahbackend.domain.pet.entity.Pet;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
public class SheetDto {
    private Long id;
    private Long petId;
    private String question;
    private String way;
    private String service;
    private String location;
    private String funeralDate;
    private SheetStatus status;
    private List<SheetFuneral> funerals;


    public SheetDto(Sheet sheet){
        this.id = sheet.getId();
        this.petId = sheet.getPet().getId();
        this.question = sheet.getQuestion();
        this.way = sheet.getWay();
        this.service = sheet.getService();
        this.location = sheet.getLocation();
        this.funeralDate = sheet.getFuneralDate();
        this.status = sheet.getStatus();
        this.funerals = sheet.getFunerals();

    }


}
