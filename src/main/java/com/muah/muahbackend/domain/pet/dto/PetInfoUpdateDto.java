package com.muah.muahbackend.domain.pet.dto;

import com.muah.muahbackend.domain.pet.entity.Gender;
import com.muah.muahbackend.domain.user.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PetInfoUpdateDto {

    private String name;
//    private Gender gender;
    private BigDecimal weight;
    private LocalDate birthdate;

}
