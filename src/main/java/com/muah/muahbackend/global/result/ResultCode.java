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
    UPLOAD_USER_IMAGE_SUCCESS(200, "U006", "유저 이미지 업로드를 성공했습니다."),
    DELETE_USER_IMAGE_SUCCESS(200, "U007", "유저 이미지 삭제를 성공했습니다. 기본이미지로 설정됩니다."),

    // Product
    UPLOAD_PRODUCT_SUCCESS(200, "P001", "상품등록에 성공했습니다."),
    GET_PRODUCT_SUCCESS(200, "P002", "상품조회에 성공했습니다."),
    GET_PRODUCT_MENU_SUCCESS(200, "P003", "상품 메뉴 조회에 성공했습니다."),
    UPDATE_PRODUCT_SUCCESS(200, "P004", "상품 수정에 성공했습니다"),
    DELETE_PRODUCT_SUCCESS(200, "P005", "상품 삭제에 성공했습니다"),
    DELETE_PRODUCT_FAILED(400, "P006", "상품 삭제에 실패했습니다"),

    // Review
    UPLOAD_REVIEW_SUCCESS(200, "R001", "상품리뷰 등록에 성공했습니다."),
    DELETE_REVIEW_SUCCESS(200, "R002", "상품리뷰 삭제에 성공했습니다"),
    DELETE_REVIEW_FAILED(400, "R003", "상품리뷰 삭제에 실패했습니다"),
    GET_REVIEW_SUCCESS(200, "R004", "내 리뷰 조회에 성공했습니다."),


    //Pet
    CREATE_PET_SUCCESS(200, "P001", "반려동물 정보를 저장했습니다."),
    UPDATE_PET_SUCCESS(200, "P002", "반려동물 정보를 업데이트했습니다."),
    DELETE_PET_SUCCESS(200, "P003", "반려동물 정보를 삭제했습니다."),
    PET_FAIL(400, "P004", "반려동물 저장, 삭제에 실패했습니다."),
    UPLOAD_PET_IMAGE_SUCCESS(200, "P005", "반려동물 이미지 업로드를 성공했습니다."),
    DELETE_PET_IMAGE_SUCCESS(200, "P006", "반려동물 이미지 삭제를 성공했습니다. 기본이미지로 설정됩니다."),

    // Sheet
    GET_SHEET_SUCCESS(200, "S002", "견적서 조회에 성공했습니다."),
    CREATE_SHEET_SUCCESS(200, "S001","견적서 생성에 성공했습니다."),
    UPDATE_SHEET_SUCCESS(200,"S003","견적서 정보를 업데이트했습니다."),
    DELETE_SHEET_SUCCESS(200,"S004","견적서 삭제에 성공했습니다."),


    //Instructor
    GET_INSTRUCTOR_SUCCESS(200,"I001","장례지도사 조회에 성공했습니다."),
    CREATE_INSTRUCTOR_SUCCESS(200,"I002","장례지도사 생성에 성공했습니다."),
    UPDATE_INSTRUCTOR_SUCCESS(200,"I003","장례지도사 정보를 업데이트했습니다."),
    DELETE_INSTRUCTOR_SUCCESS(200,"I004","장례지도사 삭제에 성공했습니다."),
    UPLOAD_INSTRUCTOR_IMAGE_SUCCESS(200, "I005","장례지도사 이미지 업로드를 성공했습니다."),
    DELETE_INSTRUCTOR_IMAGE_SUCCESS(200, "I006","장례지도사 이미지 삭제를 성공했습니다. 기본 이미지로 설정됩니다."),


    // Proposal
    GET_PROPOSAL_SUCCESS(200,"P001","견적서 댓글 조회에 성공했습니다."),
    CREATE_PROPOSAL_SUCCESS(200,"P002","견적서 댓글 생성에 성공했습니다."),
    UPDATE_PROPOSAL_SUCCESS(200,"P003","견적서 댓글 정보를 업데이트했습니다."),
    DELETE_PROPOSAL_SUCCESS(200,"P004","견적서 댓글 삭제에 성공했습니다."),



    ;


    private int status;
    private final String code;
    private final String message;
}
