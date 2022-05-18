package com.muah.muahbackend.domain.estimate.dto;


import com.muah.muahbackend.domain.estimate.entity.SheetStatus;
import com.muah.muahbackend.domain.pet.entity.Pet;
import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SheetUploadRequest {

    private Long petId;
    private String question;
    private String way;
    private String service;
    private String location;
    private String funeralDate;


}
