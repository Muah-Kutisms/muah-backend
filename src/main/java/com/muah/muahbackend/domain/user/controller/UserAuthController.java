package com.muah.muahbackend.domain.user.controller;

import com.muah.muahbackend.domain.user.dto.RegisterRequest;
import com.muah.muahbackend.domain.user.service.UserAuthService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "유저 인증 API")
@Validated
@RestController
@RequiredArgsConstructor
public class UserAuthController {
    private final UserAuthService userAuthService;

    @ApiOperation(value = "회원가입")
    @ApiImplicitParam(name = "Authorization", value = "불필요", required = false, example = " ")
    @PostMapping(value = "/accounts")
    public ResponseEntity<ResultResponse> register(@Validated @RequestBody RegisterRequest registerRequest) {
        boolean isRegistered = userAuthService.register(registerRequest);
        ResultResponse result;
        if (isRegistered) {
            result = ResultResponse.of(ResultCode.REGISTER_SUCCESS, true);
        } else {
            result = ResultResponse.of(ResultCode.REGISTER_FAIL, false);
        }
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }
}
