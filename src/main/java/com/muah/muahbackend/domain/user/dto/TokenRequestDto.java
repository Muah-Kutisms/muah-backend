package com.muah.muahbackend.domain.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequestDto {

    private String accessToken;
    private String refreshToken;
}

