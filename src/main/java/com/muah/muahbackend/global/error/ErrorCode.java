package com.muah.muahbackend.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ErrorCode Convention
 * - 도메인 별로 나누어 관리
 * - [주체_이유] 형태로 생성
 * - 코드는 도메인명 앞에서부터 1~2글자로 사용
 * - 메시지는 "~~다."로 마무리
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Global
    INTERNAL_SERVER_ERROR(500, "G001", "내부 서버 오류입니다."),
    METHOD_NOT_ALLOWED(405, "G002", "허용되지 않은 HTTP method입니다."),
    INPUT_VALUE_INVALID(400, "G003", "유효하지 않은 입력입니다."),
    INPUT_TYPE_INVALID(400, "G004", "입력 타입이 유효하지 않습니다."),
    HTTP_MESSAGE_NOT_READABLE(400, "G005", "request message body가 없거나, 값 타입이 올바르지 않습니다."),
    HTTP_HEADER_INVALID(400, "G006", "request header가 유효하지 않습니다."),
    IMAGE_TYPE_NOT_SUPPORTED(400, "G007", "지원하지 않는 이미지 타입입니다."),
    FILE_CANT_CONVERT(500, "G008", "변환할 수 없는 파일입니다."),

    // User
    USER_NOT_FOUND(400, "U001", "존재 하지 않는 유저입니다."),
    EMAIL_ALREADY_EXIST(400, "U002", "이미 존재하는 이메일입니다."),
    NEED_TO_LOGIN(401, "U003", "로그인 후 이용가능합니다."),

    NO_AUTHORITY(403, "U004", "권한이 없습니다."),
    INVALID_TOKEN(400, "U005", "유효하지 않은 토큰입니다."),

    // Product
    PRODUCT_NOT_FOUND(400, "P001", "존재 하지 않는 상품입니다."),

    // Pet
    PET_NOT_FOUND(400, "P001", "존재 하지 않는 반려동물 정보입니다."),

    // Review
    REVIEW_NOT_FOUND(400, "R001", "존재하지 않는 리뷰입니다."),

    //Sheet
    SHEET_NOT_FOUND(400, "S001", "존재하지 않는 견적서입니다."),

    //Instructor
    INSTRUCTOR_NOT_FOUND(400, "I001","존재하지 않는 장례지도사입니다."),

    //Proposal
    PROPOSAL_NOT_FOUND(400,"P001","존재하지 않는 견적서 댓글입니다.")
    ;
    private int status;
    private final String code;
    private final String message;
}
