package com.muah.muahbackend.domain.estimate.controller;

import com.muah.muahbackend.domain.estimate.dto.SheetUpdateDto;
import com.muah.muahbackend.domain.estimate.dto.SheetUploadRequest;
import com.muah.muahbackend.domain.estimate.service.SheetService;
import com.muah.muahbackend.domain.pet.service.PetService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/funeral")
@RequiredArgsConstructor
@Api(tags = "견적서 API")
public class SheetController {

    private final PetService petService;
    private final SheetService sheetService;

    @ApiOperation(value = "나의 전체 동물 조회")
    @GetMapping("/pet")
    public ResponseEntity<ResultResponse> getTotalPets() {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_USER_SUCCESS, petService.getPetList());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "id인 동물 조회")
    @GetMapping("/pet/{id}")
    public ResponseEntity<ResultResponse> getOnePet(@PathVariable Long id) {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_USER_SUCCESS, petService.getPetInfo(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }


    @ApiOperation(value = "내 견적서 조회")
    @GetMapping("/estimate")
    public ResponseEntity<ResultResponse> getSheetList() {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_SHEET_SUCCESS, sheetService.getSheetList());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "id 동물의 견적서 조회")
    @GetMapping("/estimate/pet/{id}")
    public ResponseEntity<ResultResponse> getPetSheet(@PathVariable Long id) {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_SHEET_SUCCESS, sheetService.getPetsheet(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "id인 견적서 조회")
    @GetMapping("/estimate/{id}")
    public ResponseEntity<ResultResponse> getSheets(@PathVariable Long id) {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_SHEET_SUCCESS, sheetService.getSheets(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }



    @ApiOperation(value = "id인 견적서 수정")
    @PutMapping("/estimate/customer/{id}")
    public ResponseEntity<ResultResponse> putSheet(@PathVariable Long id, @RequestBody SheetUpdateDto request){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.UPDATE_PET_SUCCESS, sheetService.updateSheet(id, request));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }



    @ApiOperation(value = "id동물의 견적서 생성")
    @PostMapping("/estimate/customer")
    public ResponseEntity<ResultResponse> postSheet(@RequestBody SheetUploadRequest request) {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.CREATE_SHEET_SUCCESS, sheetService.uploadSheet(request));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }


    @ApiOperation(value = "id인 견적서 삭제")
    @DeleteMapping("/estimate/customer/{id}")
    public ResponseEntity<ResultResponse> deleteSheet(@PathVariable Long id){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.DELETE_SHEET_SUCCESS, sheetService.deleteSheet(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }

}