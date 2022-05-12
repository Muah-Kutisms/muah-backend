package com.muah.muahbackend.domain.oauth.controller;

import com.muah.muahbackend.domain.oauth.dto.GoogleLoginDto;
import com.muah.muahbackend.domain.oauth.dto.UserLoginRequest;
import com.muah.muahbackend.domain.oauth.dto.UserLoginResponse;
import com.muah.muahbackend.domain.oauth.service.GoogleOauthService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Slf4j
public class GoogleOauthController {

    private final GoogleOauthService oauthService;

    @GetMapping(value = "/google")
    public void socialLogin() {
        log.info("구글 로그인 요청 받음");
        oauthService.request();
    }

    /**
     * Social Login API Server 요청에 의한 callback 을 처리
     * @param code API Server 로부터 넘어노는 code
     * @return AccessToken 요청을 위한 code 반환.
     */
    @GetMapping(value = "google/callback")
    public String callback(
            @RequestParam(name = "code") String code) {
        return code;
    }

    @ApiOperation(tags= "구글로그인 API", value = "구글 로그인")
    @ApiImplicitParam(name = "Authorization", value = "불필요", required = false, example = " ")
    @PostMapping(value = "/login")
    public ResponseEntity<ResultResponse> login(@RequestBody UserLoginRequest request){
        ResultResponse response = ResultResponse.of(ResultCode.REGISTER_SUCCESS, oauthService.userLogin(request.getCode()));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }
}
