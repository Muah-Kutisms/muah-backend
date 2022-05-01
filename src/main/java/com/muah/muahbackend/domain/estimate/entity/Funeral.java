package com.muah.muahbackend.domain.estimate.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="funerals")
public class Funeral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funeral_id")
    private Long id;

    @Column(name = "funeral_name")
    private String name;

    // TODO : might need more field..

    @Builder
    public Funeral(String name){
        this.name = name;
    }
}
