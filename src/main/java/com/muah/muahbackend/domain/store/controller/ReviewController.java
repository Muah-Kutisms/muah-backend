package com.muah.muahbackend.domain.store.controller;

import com.muah.muahbackend.domain.store.dto.ProductUploadRequest;
import com.muah.muahbackend.domain.store.dto.ReviewUploadRequest;
import com.muah.muahbackend.domain.store.service.ReviewService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
@Api(tags = "상품리뷰 API")
public class ReviewController {
    private final ReviewService reviewService;

    @ApiOperation(value = "상품 리뷰 등록")
    @PostMapping("/")
    public ResponseEntity<ResultResponse> uploadReview(@RequestBody ReviewUploadRequest request){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.UPLOAD_REVIEW_SUCCESS,
                reviewService.uploadReview(request));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }

    @ApiOperation(value = "내 리뷰 조회")
    @GetMapping("/")
    public ResponseEntity<ResultResponse> getReviews(){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_REVIEW_SUCCESS, reviewService.getReviews());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value="상품리뷰 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResultResponse> deleteProduct(@PathVariable Long id){
        ResultResponse response;
        if (reviewService.deleteReview(id)){
            response = ResultResponse.of(ResultCode.DELETE_REVIEW_SUCCESS);
        }
        else{
            response = ResultResponse.of(ResultCode.DELETE_REVIEW_FAILED);
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

}
