package com.muah.muahbackend.domain.pet.dto;

import com.muah.muahbackend.domain.pet.entity.Gender;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.global.vo.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PetDto {

    private Long id;
    private String name;

 //   private Gender gender;

    private BigDecimal weight;

    private LocalDate birthdate;

    private Image image;

    private Long userId;

    private String kind;


    public PetDto(Pet pet){
        this.id = pet.getId();
        this.name = pet.getName();
     //   this.gender = pet.getGender();
        this.kind = pet.getKind();
        this.weight = pet.getWeight();
        this.birthdate = pet.getBirthdate();
        this.image = pet.getImage();
        this.userId = pet.getOwner().getId();
    }

}
