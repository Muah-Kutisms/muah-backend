package com.muah.muahbackend.domain.estimate.entity;

import com.muah.muahbackend.domain.pet.entity.Gender;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.global.entity.Base;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="sheets")
public class Sheet extends Base {

    @Id
    @Column(name = "sheet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(name="sheet_pet_name")
    private String petName;

//    @Column(name="sheet_pet_gender")
//    @Enumerated(EnumType.STRING)
//    private Gender petGender;

    @Column(name="sheet_pet_weight", scale = 1)
    private BigDecimal petWeight;

    @Column(name="sheet_pet_birthdate")
    private LocalDate birthdate;

    @Column(name="sheet_question")
    private String question;

    @Column(name="sheet_funeral_date", nullable = false)
    private LocalDate funeralDate;

    @Column(name="sheet_option")
    private String option;

    @Column(name="sheet_way", nullable = false)
    private String way;

    @Column(name="sheet_service")
    private String service;

    @Column(name="sheet_location", nullable = false)
    private String location;

    @Column(name="sheet_status")
    @Enumerated(EnumType.STRING)
    private SheetStatus status;

    @OneToMany(mappedBy = "sheet")
    private List<SheetFuneral> funerals = new ArrayList<>();

    @OneToMany(mappedBy = "sheet")
    private List<Proposal> proposals = new ArrayList<>();


    // TODO : add update functions

    @Builder
    public Sheet(Pet pet, LocalDate funeralDate, String option, String question, String way, String service, String location){
        this.pet = pet;
        this.petName = pet.getName();
    //    this.petGender = pet.getGender();
        this.petWeight = pet.getWeight();
        this.birthdate = pet.getBirthdate();
        this.question = question;
        this.funeralDate = funeralDate;
        this.option = option;
        this.way = way;
        this.service = service;
        this.location = location;
        this.status = SheetStatus.WAITING_RESERVATION;
    }

}
