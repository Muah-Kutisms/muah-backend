package com.muah.muahbackend.domain.estimate.controller;


import com.muah.muahbackend.domain.user.dto.TokenDto;
import com.muah.muahbackend.domain.user.dto.TokenRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funeral/estimate")
@RequiredArgsConstructor
@Api(tags = "견적서 생성 API")
public class SheetController {

    @ApiOperation(value = "견적서 조회")
    @GetMapping("/")
    public void getSheet(@RequestBody TokenRequestDto tokenRequestDto) {


    }

    @ApiOperation(value = "견적서 조회")
    @PostMapping("/")
    public void postSheet(@RequestBody TokenRequestDto tokenRequestDto) {


    }

}
