package com.muah.muahbackend.domain.estimate.controller;

import com.muah.muahbackend.domain.estimate.dto.SheetUploadRequest;
import com.muah.muahbackend.domain.estimate.service.SheetService;
import com.muah.muahbackend.domain.store.dto.ReviewUploadRequest;
import com.muah.muahbackend.domain.user.dto.TokenRequestDto;
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
@RequestMapping("/funeral")
@RequiredArgsConstructor
@Api(tags = "견적서 생성 API")
public class SheetController {

    private final PetService petService;
    private final SheetService sheetService;

    @ApiOperation(value = "전체 동물 조회")
    @GetMapping("/pet")
    public ResponseEntity<ResultResponse> getTotalPets() {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_PET_SUCCESS, petService.getPetList());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "id인 동물 조회")
    @GetMapping("/pet/{id}")
    public ResponseEntity<ResultResponse> getOnePet(@PathVariable Long id) {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_PET_SUCCESS, petService.getPet(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }


    @ApiOperation(value = "내 견적서 조회")
    @GetMapping("/estimate")
    public ResponseEntity<ResultResponse> getSheetList() {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_SHEET_SUCCESS, sheetService.getSheetList());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "id동물의 견적서 조회")
    @GetMapping("/estimate/{id}")
    public ResponseEntity<ResultResponse> getSheets(@PathVariable Long id) {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_SHEET_SUCCESS, sheetService.getSheets(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }



    @ApiOperation(value = "id동물의 견적서 수정")
    @PutMapping("/estimate/customer/{id}")
    public void putSheet(@RequestBody TokenRequestDto tokenRequestDto) {


    }

    @ApiOperation(value = "id동물의 견적서 생성")
    @PostMapping("/estimate/customer/{id}")
    public ResponseEntity<ResultResponse> postSheet(@RequestBody SheetUploadRequest request) {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.UPLOAD_REVIEW_SUCCESS,
                sheetService.uploadSheet(request));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }



    @ApiOperation(value = "id동물의 견적서 삭제")
    @DeleteMapping("/estimate/customer/{id}")
    public void deleteSheet(@RequestBody TokenRequestDto tokenRequestDto) {


    }

}
