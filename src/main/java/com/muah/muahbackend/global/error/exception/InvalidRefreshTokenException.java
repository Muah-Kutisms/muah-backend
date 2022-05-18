package com.muah.muahbackend.global.error.exception;

import com.muah.muahbackend.global.error.ErrorCode;

public class InvalidRefreshTokenException extends BusinessException{
    public InvalidRefreshTokenException() { super(ErrorCode.INVALID_TOKEN);
    }
}
