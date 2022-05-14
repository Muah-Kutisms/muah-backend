package com.muah.muahbackend.domain.pet.dto;

import com.muah.muahbackend.domain.pet.entity.Gender;
import com.muah.muahbackend.domain.pet.entity.Pet;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PetDto {
    private Long id;
    private String name;
    private Gender gender;
    private BigDecimal weight;
    private LocalDate birthdate;

    public PetDto(Pet pet){
        this.id=pet.getId();
        this.name = pet.getName();
        this.gender = pet.getGender();
        this.weight = pet.getWeight();
        this.birthdate = pet.getBirthdate();
    }
}