package com.muah.muahbackend.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    // AUTH
    REGISTER_SUCCESS(200, "A001", "회원가입에 성공하였습니다."),
    REGISTER_FAIL(200, "A002", "회원가입에 실패했습니다."),

    // User
    UPDATE_SUCCESS(200, "U003", "유저정보를 업데이트했습니다."),
    UPDATE_FAIL(400, "U004", "유저정보를 업데이트에 실패했습니다."),
    GET_USER_SUCCESS(200,  "U005", "유저정보를 조회했습니다."),

    //Product
    UPLOAD_PRODUCT_SUCCESS(200, "P001", "상품등록에 성공했습니다."),
    GET_PRODUCT_SUCCESS(200, "P002", "상품조회에 성공했습니다."),
    GET_PRODUCT_MENU_SUCCESS(200, "P003", "상품 메뉴 조회에 성공했습니다."),

    //Review
    UPLOAD_REVIEW_SUCCESS(200, "R001", "상품리뷰 등록에 성공했습니다."),

    ;


    private int status;
    private final String code;
    private final String message;
}
