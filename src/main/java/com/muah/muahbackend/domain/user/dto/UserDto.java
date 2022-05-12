package com.muah.muahbackend.domain.user.dto;

import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.store.entity.Product;
import com.muah.muahbackend.domain.user.entity.Address;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private String phone;
    private AddressDto address;
    private UserRole role;
    private Boolean isApproved;
    private Boolean isNew;
    private List<Pet> pets;

    public UserDto(User user, List<Pet> pets){
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.address = new AddressDto(user.getAddress());
        this.role = user.getRole();
        this.isApproved = user.getIsApproved();
        this.isNew = user.getIsNew();
    }

}
