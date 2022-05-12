package com.muah.muahbackend.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    REGISTER_SUCCESS(200, "U001", "회원가입에 성공하였습니다."),
    REGISTER_FAIL(200, "U002", "회원가입에 실패했습니다.");

    private int status;
    private final String code;
    private final String message;
}
