package com.muah.muahbackend.global.error.exception;

import com.muah.muahbackend.global.error.ErrorCode;

public class CantConvertFileException extends BusinessException {
    public CantConvertFileException() {
        super(ErrorCode.FILE_CANT_CONVERT);
    }
}
