package com.muah.muahbackend.domain.user.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String City;
    private String County;
    private String District;
}
