package com.muah.muahbackend.domain.estimate.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SheetUpdateDto {


    private String question;
    private String way;
    private String service;
    private String location;
    private String funeralDate;
}
