package com.muah.muahbackend.domain.user.dto;

import com.muah.muahbackend.domain.pet.dto.PetDto;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.user.entity.Address;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.entity.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private String phone;
    private Address address;
    private UserRole role;
    private Boolean isApproved;
    private Boolean isNew;
    private List<PetDto> pets;

    public UserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.role = user.getRole();
        this.isApproved = user.getIsApproved();
        this.isNew = user.getIsNew();
    }

}
