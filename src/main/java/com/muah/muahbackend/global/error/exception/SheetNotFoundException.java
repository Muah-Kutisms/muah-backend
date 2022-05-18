package com.muah.muahbackend.global.error.exception;

import com.muah.muahbackend.global.error.ErrorCode;

public class SheetNotFoundException extends BusinessException{
    public SheetNotFoundException() { super(ErrorCode.SHEET_NOT_FOUND);
    }
}
