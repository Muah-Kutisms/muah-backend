package com.muah.muahbackend.global.error.exception;

import com.muah.muahbackend.global.error.ErrorCode;


public class InstructorNotFoundException extends BusinessException{
    public InstructorNotFoundException() { super(ErrorCode.INSTRUCTOR_NOT_FOUND);
    }
}