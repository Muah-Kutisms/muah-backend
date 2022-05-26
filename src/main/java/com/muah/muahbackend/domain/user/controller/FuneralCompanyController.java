package com.muah.muahbackend.domain.user.controller;


import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.domain.user.service.FuneralCompanyService;
import com.muah.muahbackend.domain.user.service.UserService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
@Api(tags = "장례회사 API")
public class FuneralCompanyController {

    private final FuneralCompanyService funeralCompanyService;

    @ApiOperation(value = "회사의 마이페이지")
    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getCompnayPage(@PathVariable Long id){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_PROPOSAL_SUCCESS,
                funeralCompanyService.getSheets(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "댓글 달아 줄 전체 sheet 보기")
    @GetMapping("/")
    public ResponseEntity<ResultResponse> getAllSheets(){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_PROPOSAL_SUCCESS,
                funeralCompanyService.getAllSheets());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }


}
