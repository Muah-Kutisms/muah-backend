package com.muah.muahbackend.domain.user.dto;

import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.entity.UserRole;
import com.muah.muahbackend.global.vo.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralUserDto extends UserDto{

    private String name;
    private String nickname;
    private String phone;
    private Image image;
    private int reservationCount;

    public GeneralUserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickname = user.getNickName();
        this.phone = user.getPhone();
        this.role = user.getRole();
        this.isApproved = user.getIsApproved();
        this.image = user.getImage();
        this.isNew = user.getIsNew();
    }

}
