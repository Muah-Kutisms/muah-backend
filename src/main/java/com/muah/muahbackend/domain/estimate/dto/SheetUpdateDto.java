package com.muah.muahbackend.domain.estimate.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SheetUpdateDto {


    private String question;
    private String way;
    private String service;
    private String location;
    private LocalDate funeralDate;
    private String option;

}
