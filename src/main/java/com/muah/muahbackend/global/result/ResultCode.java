package com.muah.muahbackend.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    // AUTH
    REGISTER_SUCCESS(200, "A001", "로그인에 성공하였습니다."),
    REGISTER_FAIL(200, "A002", "로그인에 실패했습니다."),

    // User
    UPDATE_SUCCESS(200, "U003", "유저정보를 업데이트했습니다."),
    UPDATE_FAIL(400, "U004", "유저정보를 업데이트에 실패했습니다."),
    GET_USER_SUCCESS(200,  "U005", "유저정보를 조회했습니다."),

    //Product
    UPLOAD_PRODUCT_SUCCESS(200, "P001", "상품등록에 성공했습니다."),
    GET_PRODUCT_SUCCESS(200, "P002", "상품조회에 성공했습니다."),
    GET_PRODUCT_MENU_SUCCESS(200, "P003", "상품 메뉴 조회에 성공했습니다."),
    UPDATE_PRODUCT_SUCCESS(200, "P004", "상품 수정에 성공했습니다"),
    DELETE_PRODUCT_SUCCESS(200, "P005", "상품 삭제에 성공했습니다"),
    DELETE_PRODUCT_FAILED(400, "P006", "상품 삭제에 실패했습니다"),

    //Review
    UPLOAD_REVIEW_SUCCESS(200, "R001", "상품리뷰 등록에 성공했습니다."),
    DELETE_REVIEW_SUCCESS(200, "R002", "상품리뷰 삭제에 성공했습니다"),
    DELETE_REVIEW_FAILED(400, "R003", "상품리뷰 삭제에 실패했습니다"),
    GET_REVIEW_SUCCESS(200, "R004", "내 리뷰 조회에 성공했습니다."),


    //Pet
    CREATE_PET_SUCCESS(200, "P001", "반려동물 정보를 저장했습니다."),
    UPDATE_PET_SUCCESS(200, "P002", "반려동물 정보를 업데이트했습니다."),
    DELELET_PET_SUCCESS(200, "P003", "반려동물 정보를 삭제했습니다."),
    PET_FAIL(400, "P004", "반려동물 저장, 삭제에 실패했습니다.")

    ;


    private int status;
    private final String code;
    private final String message;
}
