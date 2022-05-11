package com.muah.muahbackend.global.error.exception;

import com.muah.muahbackend.global.error.ErrorCode;

public class UserNotFoundException extends BusinessException{
    public UserNotFoundException() { super(ErrorCode.USER_NOT_FOUND);
    }
}
