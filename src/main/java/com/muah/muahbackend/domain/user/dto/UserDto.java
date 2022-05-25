package com.muah.muahbackend.domain.user.dto;

import com.muah.muahbackend.domain.user.entity.UserRole;

public abstract class UserDto {

    public Long id;
    public String email;
    public UserRole role;
    public Boolean isApproved;
    public Boolean isNew;
}
