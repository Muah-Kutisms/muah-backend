package com.muah.muahbackend.domain.estimate.entity;

import com.muah.muahbackend.domain.pet.entity.Gender;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.global.entity.Base;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="sheets")
public class Sheet extends Base {

    @Id
    @Column(name = "sheet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(name="sheet_pet_name")
    private String petName;

    @Column(name="sheet_pet_gender")
    @Enumerated(EnumType.STRING)
    private Gender petGender;

    @Column(name="sheet_pet_weight", scale = 1)
    private BigDecimal petWeight;

    @Column(name="sheet_pet_birthdate")
    private LocalDate birthdate;

    @Column(name="sheet_question")
    private String question;

    @Column(name="sheet_funeral_date", nullable = false)
    private LocalDate funeralDate;

    @Column(name="sheet_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    // TODO : location 이 원하는 장례식장 위치? 내 현재 위치?

    // TODO : add update functions

    @Builder
    public Sheet(Pet pet, LocalDate funeralDate){
        this.pet = pet;
        this.petName = pet.getName();
        this.petGender = pet.getGender();
        this.petWeight = pet.getWeight();
        this.birthdate = pet.getBirthdate();
        this.funeralDate = funeralDate;
        this.status = Status.WAITING_RESERVATION;
    }

}
