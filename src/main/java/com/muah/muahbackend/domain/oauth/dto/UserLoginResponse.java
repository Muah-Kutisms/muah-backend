package com.muah.muahbackend.domain.oauth.dto;

import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.user.entity.Address;
import com.muah.muahbackend.domain.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginResponse {
    private Long id;
    private Boolean isNew;
    private String accessToken;
    private String refreshToken;

}
