package com.muah.muahbackend.domain.user.dto;

import com.muah.muahbackend.domain.pet.dto.PetDto;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.user.entity.Address;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.entity.UserRole;
import com.muah.muahbackend.global.vo.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private String nickname;
    private String phone;
    private UserRole role;
    private Boolean isApproved;
    private Boolean isNew;
    private Image image;
    private int reservationCount;

    public UserDto(User user){
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
