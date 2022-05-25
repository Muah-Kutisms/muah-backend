package com.muah.muahbackend.domain.user.dto;

import com.muah.muahbackend.domain.user.entity.Address;
import com.muah.muahbackend.domain.user.entity.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoUpdateDto {

    private String name;
    private String nickName;
    private String funeralName;
    private String phone;
    private AddressDto address;
    private UserRole role;
    private Boolean isApproved;
    private Boolean isNew;


}
