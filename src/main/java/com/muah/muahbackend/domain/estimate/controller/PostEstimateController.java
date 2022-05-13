package com.muah.muahbackend.domain.estimate.controller;


import com.muah.muahbackend.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;


@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/funeral")
@Slf4j
public class PostEstimateController {

    @GetMapping("/estimate/customer")
    public String getPage(){

        // login한 user 받기

        // 선택한 동물


        return "";
    }


}
