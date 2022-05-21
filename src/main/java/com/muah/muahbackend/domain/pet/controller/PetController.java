package com.muah.muahbackend.domain.pet.controller;

import com.muah.muahbackend.domain.pet.dto.PetDto;
import com.muah.muahbackend.domain.pet.dto.PetInfoUpdateDto;
import com.muah.muahbackend.domain.pet.dto.PetRegisterRequest;
import com.muah.muahbackend.domain.pet.service.PetService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
@Api(tags = "반려동물 API")
public class PetController {
    private final PetService petService;

    @ApiOperation(value = "반려동물 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getUser(@PathVariable Long id){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_USER_SUCCESS,
                petService.getPetInfo(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "반려동물 정보 저장")
    @PostMapping(value="/",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResultResponse> createPetInfo(@RequestPart PetRegisterRequest request,
                                                        @RequestPart  MultipartFile imgFile) {
        ResultResponse response;
        try {
            response = ResultResponse.of(ResultCode.CREATE_PET_SUCCESS,
                    new PetDto(petService.createPetInfo(request, imgFile)));
        } catch (Exception e){
            System.out.println(e.getCause());
            response = ResultResponse.of(ResultCode.PET_FAIL, e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "반려동물 정보 업데이트")
    @PutMapping("/{id}")
    public ResponseEntity<ResultResponse> updatePetInfo(@RequestBody PetInfoUpdateDto request,
                                                        @PathVariable Long id ) {
        ResultResponse response;
        try {
            response = ResultResponse.of(ResultCode.UPDATE_PET_SUCCESS,
                    petService.updatePetInfo(request, id));
        } catch (Exception e){
            response = ResultResponse.of(ResultCode.PET_FAIL, e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "반려동물 정보 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResultResponse> deletePetInfo(@PathVariable Long id ) {
        ResultResponse response;
        try {
            response = ResultResponse.of(ResultCode.DELETE_PET_SUCCESS,
                    petService.deletePetInfo(id));
        } catch (Exception e){
            response = ResultResponse.of(ResultCode.PET_FAIL, e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "반려동물 사진 업로드")
    @PostMapping(value = "/image/{id}")
    public ResponseEntity<ResultResponse> uploadImage (@RequestParam MultipartFile uploadedImage,
                                                       @PathVariable Long id){
        petService.uploadUserImage(uploadedImage,id);
        ResultResponse result = ResultResponse.of(ResultCode.UPLOAD_PET_IMAGE_SUCCESS,null);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }

    @ApiOperation(value = "반려동물 사진 삭제")
    @DeleteMapping(value = "/image/{id}")
    public ResponseEntity<ResultResponse> deleteImage (@PathVariable Long id){
        petService.deleteUserImage(id);
        ResultResponse result = ResultResponse.of(ResultCode.DELETE_PET_IMAGE_SUCCESS,null);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }



}