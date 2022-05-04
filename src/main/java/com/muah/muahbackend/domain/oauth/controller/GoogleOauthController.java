package com.muah.muahbackend.domain.oauth.controller;

import com.muah.muahbackend.domain.oauth.service.GoogleOauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
