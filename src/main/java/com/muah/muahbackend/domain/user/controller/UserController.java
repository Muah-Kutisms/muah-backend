package com.muah.muahbackend.domain.user.controller;

import com.muah.muahbackend.domain.user.dto.TokenDto;
import com.muah.muahbackend.domain.user.dto.TokenRequestDto;
import com.muah.muahbackend.domain.user.dto.UserInfoUpdateDto;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.domain.user.service.UserImageService;
import com.muah.muahbackend.domain.user.service.UserService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Api(tags = "유저 API")
public class UserController {
    private final UserService userService;
    private final UserImageService userImageService;

    @ApiOperation(value = "유저 정보 업데이트")
    @PutMapping("/{id}")
    public ResponseEntity<ResultResponse> updateUserInfo(@RequestBody UserInfoUpdateDto request,
                                                         @PathVariable Long id ) {
        ResultResponse response;
        try {
            response = ResultResponse.of(ResultCode.UPDATE_SUCCESS,
                    userService.updateUserInfo(request, id));
        } catch (Exception e){
            response = ResultResponse.of(ResultCode.UPDATE_FAIL, e.getMessage());
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

    @ApiOperation(value = "유저 사진 업로드")
    @PostMapping(value = "/image/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResultResponse> uploadImage (@RequestPart MultipartFile imgFile,
                                                       @PathVariable Long id){
        userImageService.uploadUserImage(imgFile,id);
        ResultResponse result = ResultResponse.of(ResultCode.UPLOAD_USER_IMAGE_SUCCESS,null);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }

    @ApiOperation(value = "유저 사진 삭제")
    @DeleteMapping(value = "/image/{id}")
    public ResponseEntity<ResultResponse> deleteImage (@PathVariable Long id){
        userImageService.deleteUserImage(id);
        ResultResponse result = ResultResponse.of(ResultCode.DELETE_USER_IMAGE_SUCCESS,null);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }
}