package com.muah.muahbackend.global.error.exception;

import com.muah.muahbackend.global.error.ErrorCode;

public class ReviewNotFoundException extends BusinessException{
    public ReviewNotFoundException() { super(ErrorCode.REVIEW_NOT_FOUND);
    }
}
