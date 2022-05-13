package com.muah.muahbackend.domain.store.controller;

import com.muah.muahbackend.domain.store.dto.ProductUploadRequest;
import com.muah.muahbackend.domain.store.dto.ProductUploadResponse;
import com.muah.muahbackend.domain.store.service.ProductService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Api(tags = "상품 API")
public class ProductController {
    private final ProductService productService;

    @ApiOperation(value = "상품 등록")
    @PostMapping("/")
    public ResponseEntity<ResultResponse> uploadProduct(@RequestBody ProductUploadRequest request){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.UPLOAD_PRODUCT_SUCCESS,
                productService.uploadProduct(request));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }

    @ApiOperation(value = "상품 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getProduct(@PathVariable Long id){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_PRODUCT_SUCCESS,
                productService.getProduct(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "상품 메뉴 조회")
    @GetMapping("/")
    public ResponseEntity<ResultResponse> getProductMenu(){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_PRODUCT_SUCCESS,
                productService.getProductMenu());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

}
