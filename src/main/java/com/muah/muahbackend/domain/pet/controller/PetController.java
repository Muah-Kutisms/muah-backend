package com.muah.muahbackend.domain.pet.controller;

import com.muah.muahbackend.domain.pet.dto.PetDto;
import com.muah.muahbackend.domain.pet.dto.PetInfoUpdateDto;
import com.muah.muahbackend.domain.pet.service.PetService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/")
    public ResponseEntity<ResultResponse> createPetInfo(@RequestBody PetDto request) {
        ResultResponse response;
        System.out.println("접속1" + request);
        try {
            System.out.printf("접속2");
            response = ResultResponse.of(ResultCode.CREATE_PET_SUCCESS,
                    petService.createPetInfo(request));
            System.out.printf(String.valueOf(response));
        } catch (Exception e){
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
            response = ResultResponse.of(ResultCode.UPDATE_SUCCESS,
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
            response = ResultResponse.of(ResultCode.DELELT_PET_SUCCESS,
                    petService.deletePetInfo(id));
        } catch (Exception e){
            response = ResultResponse.of(ResultCode.PET_FAIL, e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

}