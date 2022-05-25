package com.muah.muahbackend.domain.pet.dto;

import com.muah.muahbackend.domain.pet.entity.Gender;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.global.vo.Image;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PetRegisterRequest {

    private String name;

  //  private Gender gender;

    private BigDecimal weight;

    private String birthdate;
    private String kind;
    private Long userId;

//
//    @Builder
//    public PetRegisterRequest(Pet pet) {
//        this.name = pet.getName();
//        this.gender = pet.getGender();
//        this.weight = pet.getWeight();
//        this.birthdate = pet.getBirthdate();
//        this.kind = pet.getKind();
//        this.userId = pet.getOwner().getId();
//    }
}