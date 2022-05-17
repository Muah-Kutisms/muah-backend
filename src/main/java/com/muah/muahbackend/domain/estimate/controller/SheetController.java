package com.muah.muahbackend.domain.estimate.controller;

import com.muah.muahbackend.domain.estimate.service.SheetService;
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

    @ApiOperation(value = "동물 조회")
    @GetMapping("/pet")
    public ResponseEntity<ResultResponse> getPets() {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_PET_SUCCESS, petService.getPets());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }


    @ApiOperation(value = "동물마다 견적서 조회")
    @GetMapping("/estimate")
    public ResponseEntity<ResultResponse> getSheets(Long id) {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_SHEET_SUCCESS, sheetService.getSheets(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }


    @ApiOperation(value = "동물마다 견적서 수정")
    @PutMapping("/estimate/customer/{id}")
    public void putSheet(@RequestBody TokenRequestDto tokenRequestDto) {


    }

    @ApiOperation(value = "동물마다 견적서 생성")
    @PostMapping("/estimate/customer/{id}")
    public void postSheet(@RequestBody TokenRequestDto tokenRequestDto) {


    }

    @ApiOperation(value = "동물마다 견적서 삭제")
    @DeleteMapping("/estimate/customer/{id}")
    public void deleteSheet(@RequestBody TokenRequestDto tokenRequestDto) {


    }

}
