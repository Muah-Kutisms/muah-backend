package com.muah.muahbackend.domain.user.controller;

import com.muah.muahbackend.domain.user.dto.TokenDto;
import com.muah.muahbackend.domain.user.dto.TokenRequestDto;
import com.muah.muahbackend.domain.user.dto.UserInfoUpdateDto;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.domain.user.service.UserService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Api(tags = "유저 API")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @ApiOperation(value = "유저 정보 업데이트")
    @PutMapping("/{id}")
    public ResponseEntity<ResultResponse> updateUserInfo(@RequestBody UserInfoUpdateDto request,
                                                         @PathVariable Long id ) {
        ResultResponse response;
        try {
            response = ResultResponse.of(ResultCode.UPDATE_SUCCESS,
                    userService.updateUserInfo(request, id));
        } catch (Exception e){
            response = ResultResponse.of(ResultCode.REGISTER_FAIL, e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "유저 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getUser(@PathVariable Long id){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_USER_SUCCESS,
                userService.getUserInfo(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
