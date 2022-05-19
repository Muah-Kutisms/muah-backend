package com.muah.muahbackend.domain.instructor.Dto;

import com.muah.muahbackend.domain.instructor.entity.Instructor;
import com.muah.muahbackend.domain.pet.entity.Gender;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.global.vo.Image;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class InstructorRegisterRequest {

    private Long userId;
    private String content;


}
