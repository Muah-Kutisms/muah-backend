package com.muah.muahbackend.global.error.exception;

import com.muah.muahbackend.global.error.ErrorCode;

public class NotSupportImageTypeException extends BusinessException {
    public NotSupportImageTypeException() {
        super(ErrorCode.IMAGE_TYPE_NOT_SUPPORTED);
    }
}
