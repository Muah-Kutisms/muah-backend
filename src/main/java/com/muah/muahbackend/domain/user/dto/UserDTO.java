package com.muah.muahbackend.domain.user.dto;

import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.user.entity.Address;
import com.muah.muahbackend.domain.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String nickname;
    private String phone;
    private Address address;
    private UserRole role;
    private List<Pet> pets;

    public UserDTO(Long id, String email, String nickname, String phone, Address address, UserRole role) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }
}
