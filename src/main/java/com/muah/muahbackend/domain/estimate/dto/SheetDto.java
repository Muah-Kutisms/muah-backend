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
    private Pet pet;
    private String petName;
    private Gender petGender;
    private BigDecimal petWeight;
    private LocalDate birthdate;
    private String question;
    private LocalDate funeralDate;
    private SheetStatus status;
    private List<SheetFuneral> funerals;

    public SheetDto(Sheet sheet){
        this.id = sheet.getId();
        this.pet = sheet.getPet();
        this.petName = sheet.getPetName();
        this.petGender = sheet.getPetGender();
        this.petWeight = sheet.getPetWeight();
        this.birthdate = sheet.getBirthdate();
        this.question = sheet.getQuestion();
        this.funeralDate = sheet.getFuneralDate();
        this.status = sheet.getStatus();
        this.funerals = sheet.getFunerals();

    }


}
