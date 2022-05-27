package com.muah.muahbackend.domain.estimate.controller;


import com.muah.muahbackend.domain.estimate.dto.ProposalUpdateStatusDto;
import com.muah.muahbackend.domain.estimate.dto.SheetUpdaateStatusDto;
import com.muah.muahbackend.domain.estimate.entity.ProposalStatus;
import com.muah.muahbackend.domain.estimate.entity.SheetStatus;
import com.muah.muahbackend.domain.estimate.service.StatusService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
@Api(tags = "견적서 status API")
public class StatusController {

    private final StatusService statusService;

    @ApiOperation(value = "id인 견적서 status 수정 ")
    @PutMapping("/sheet/{id}")
    public ResponseEntity<ResultResponse> putSheetStatus(@PathVariable Long id){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.UPDATE_SHEET_SUCCESS, statusService.updateSheetStatus(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }

    @ApiOperation(value = "id인 견적서 status 수정 ")
    @PutMapping("/proposal/{id}")
    public ResponseEntity<ResultResponse> putProposalStatus(@PathVariable Long id){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.UPDATE_SHEET_SUCCESS, statusService.updateProposalStatus(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }


}
