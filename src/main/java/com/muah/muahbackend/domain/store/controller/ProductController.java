package com.muah.muahbackend.domain.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muah.muahbackend.domain.store.dto.ProductUpdateDto;
import com.muah.muahbackend.domain.store.dto.ProductUploadRequest;
import com.muah.muahbackend.domain.store.dto.ProductUploadResponse;
import com.muah.muahbackend.domain.store.entity.Product;
import com.muah.muahbackend.domain.store.service.ProductService;
import com.muah.muahbackend.global.error.ErrorCode;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;

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


    @ApiOperation(value = "상품 수정")
    @PutMapping("/{id}")
    public ResponseEntity<ResultResponse> updateProduct(@PathVariable Long id,
                                                        @RequestBody ProductUpdateDto request){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.UPDATE_PRODUCT_SUCCESS, productService.updateProduct(id, request));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value="상품 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResultResponse> deleteProduct(@PathVariable Long id){
        ResultResponse response;
        if (productService.deleteProduct(id)){
            response = ResultResponse.of(ResultCode.DELETE_PRODUCT_SUCCESS);
        }
        else{
            response = ResultResponse.of(ResultCode.DELETE_PRODUCT_FAILED);
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

}
