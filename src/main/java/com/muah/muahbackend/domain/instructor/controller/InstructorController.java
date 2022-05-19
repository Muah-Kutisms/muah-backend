package com.muah.muahbackend.domain.instructor.controller;


import com.muah.muahbackend.domain.estimate.dto.SheetUpdateDto;
import com.muah.muahbackend.domain.estimate.dto.SheetUploadRequest;
import com.muah.muahbackend.domain.instructor.Dto.InstructorRegisterRequest;
import com.muah.muahbackend.domain.instructor.Dto.InstructorUpdateDto;
import com.muah.muahbackend.domain.instructor.service.InstructorService;
import com.muah.muahbackend.global.result.ResultCode;
import com.muah.muahbackend.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/funeral/instructor")
@RequiredArgsConstructor
@Api(tags = "장례지도사 API")
public class InstructorController {

    private final InstructorService instructorService;

    @ApiOperation(value = "전체 장례지도사 조회")
    @GetMapping("/")
    public ResponseEntity<ResultResponse> getTotalInsts() {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_USER_SUCCESS, instructorService.getInstructorList());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ApiOperation(value = "id인 장례지도사 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getOneInst(@PathVariable Long id) {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.GET_USER_SUCCESS, instructorService.getInstructorInfo(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }



    @ApiOperation(value = "id 장례지도사 수정")
    @PutMapping("/{id}")
    public ResponseEntity<ResultResponse> putInst(@RequestBody InstructorUpdateDto request, @PathVariable Long id){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.UPDATE_INSTRUCTOR_SUCCESS, instructorService.updateInstructorInfo(request, id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }


    @ApiOperation(value = "장례지도사 생성")
    @PostMapping("/")
    public ResponseEntity<ResultResponse> postInst(@RequestBody InstructorRegisterRequest request) {
        ResultResponse response;
        response = ResultResponse.of(ResultCode.CREATE_INSTRUCTOR_SUCCESS, instructorService.createInstructorInfo(request));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }


    @ApiOperation(value = "id 장례지도사 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResultResponse> deleteSheet(@PathVariable Long id){
        ResultResponse response;
        response = ResultResponse.of(ResultCode.DELETE_INSTRUCTOR_SUCCESS, instructorService.deleteInstructor(id));
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));

    }

    @ApiOperation(value = "장례지도사 사진 업로드")
    @PostMapping(value = "/image/{id}")
    public ResponseEntity<ResultResponse> uploadImage (@RequestParam MultipartFile uploadedImage,
                                                       @PathVariable Long id){
        instructorService.uploadInstructorImage(uploadedImage,id);
        ResultResponse result = ResultResponse.of(ResultCode.UPLOAD_INSTRUCTOR_IMAGE_SUCCESS,null);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }

    @ApiOperation(value = "장례지도사 사진 삭제")
    @DeleteMapping(value = "/image/{id}")
    public ResponseEntity<ResultResponse> deleteImage (@PathVariable Long id){
        instructorService.deleteInstructorImage(id);
        ResultResponse result = ResultResponse.of(ResultCode.DELETE_INSTRUCTOR_IMAGE_SUCCESS,null);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }

}
