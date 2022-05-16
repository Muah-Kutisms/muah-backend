package com.muah.muahbackend.domain.pet.entity;

import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.global.entity.Base;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="pets")
public class Pet extends Base {
    @Id
    @Column(name = "pet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name="pet_name")
    private String name;

    @Column(name="pet_gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="pet_weight", scale = 1)
    private BigDecimal weight;

    @Column(name="pet_birthdate")
    private LocalDate birthdate;


    // TODO : 이미지 필드

    @OneToMany(mappedBy = "pet")
    private List<Sheet> sheets = new ArrayList<>();

    @Builder
    public Pet(User owner, String name, Gender gender, BigDecimal weight, LocalDate birthdate){
        this.owner = owner;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.birthdate = birthdate;

        // TODO : 이미지 기본이미지로 초기화 (image builder)
    }

}
