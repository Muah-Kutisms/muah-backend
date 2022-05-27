package com.muah.muahbackend.domain.estimate.controller;

import com.muah.muahbackend.domain.estimate.dto.ProposalUpdateDto;
import com.muah.muahbackend.domain.estimate.dto.ProposalUploadRequest;
import com.muah.muahbackend.domain.estimate.service.ProposalService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/funeral/estimate/company")
@RequiredArgsConstructor
@Api(tags = "업체 견적서 API")
public class ProposalController {

    private final ProposalService proposalService;


    @ApiOperation(value = "id 견적서에 전체 업체의 댓글 조회")
    @GetMapping("/sheet/{sheetId}")
    public ResponseEntity<ResultResponse> getProposalList(@PathVariable Long sheetId){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_PROPOSAL_SUCCESS, proposalService.getProposalList(sheetId));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }

    @ApiOperation(value = "id 댓글 조회")
    @GetMapping("/proposal/{proposalId}")
    public ResponseEntity<ResultResponse> getProposalInfo(@PathVariable Long proposalId){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_PROPOSAL_SUCCESS, proposalService.getProposalInfo(proposalId));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "id 견적서에 업체의 댓글 작성")
    @PostMapping("/proposal")
    public ResponseEntity<ResultResponse> postProposal(@RequestBody ProposalUploadRequest request){
        ResultResponse response;
        Long sheetId = request.getSheetId();
        response = ResultResponse.of(ResultCode.CREATE_PROPOSAL_SUCCESS, proposalService.uploadProposal(sheetId, request));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }

    @ApiOperation(value = "id 견적서에 업체의 댓글 id 수정")
    @PutMapping("/proposal/{proposalId}")
    public ResponseEntity<ResultResponse> putProposal(@PathVariable Long proposalId,
                                                      @RequestBody ProposalUpdateDto request){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.UPDATE_PROPOSAL_SUCCESS, proposalService.updateProposal(proposalId, request));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "id 견적서에 업체의 댓글 id 삭제")
    @DeleteMapping("/proposal/{proposalId}")
    public ResponseEntity<ResultResponse> deleteProposal(@PathVariable Long proposalId){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.DELETE_PROPOSAL_SUCCESS, proposalService.deleteProposal(proposalId));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }



}
