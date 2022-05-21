package com.muah.muahbackend.domain.estimate.dto;


import com.muah.muahbackend.domain.estimate.entity.SheetStatus;
import com.muah.muahbackend.domain.pet.entity.Pet;
import lombok.*;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class SheetUploadRequest {

    private Long petId;
    private String question;
    private String way;
    private String service;
    private String location;
    private LocalDate funeralDate;
    private String option;

}
