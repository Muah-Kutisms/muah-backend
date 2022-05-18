package com.muah.muahbackend.global.error.exception;

import com.muah.muahbackend.global.error.ErrorCode;

public class PetNotFoundException extends BusinessException{
    public PetNotFoundException() { super(ErrorCode.PET_NOT_FOUND);
    }
}
