package com.muah.muahbackend.global.error.exception;

import com.muah.muahbackend.global.error.ErrorCode;

public class ProductNotFoundException extends BusinessException{

    public ProductNotFoundException() {super(ErrorCode.PRODUCT_NOT_FOUND);}
}
