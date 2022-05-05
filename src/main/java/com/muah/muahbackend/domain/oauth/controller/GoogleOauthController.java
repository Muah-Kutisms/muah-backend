package com.muah.muahbackend.domain.oauth.controller;

import com.muah.muahbackend.domain.oauth.dto.GoogleLoginDto;
import com.muah.muahbackend.domain.oauth.service.GoogleOauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
     * @return SNS Login 요청 결과로 받은 Json 형태의 String 문자열 (access_token, refresh_token 등)
     */
    @GetMapping(value = "google/callback")
    public ResponseEntity<GoogleLoginDto> callback(
            @RequestParam(name = "code") String code) {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
        return oauthService.requestAccessToken(code);
    }
}
