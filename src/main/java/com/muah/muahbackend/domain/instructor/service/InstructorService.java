package com.muah.muahbackend.domain.instructor.service;


import com.muah.muahbackend.domain.instructor.Dto.InstructorDto;
import com.muah.muahbackend.domain.instructor.Dto.InstructorRegisterRequest;
import com.muah.muahbackend.domain.instructor.Dto.InstructorRegisterResponse;
import com.muah.muahbackend.domain.instructor.Dto.InstructorUpdateDto;
import com.muah.muahbackend.domain.instructor.entity.Instructor;
import com.muah.muahbackend.domain.instructor.repository.InstructorRepository;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.InstructorNotFoundException;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import com.muah.muahbackend.global.vo.Image;
import com.muah.muahbackend.infra.aws.S3Uploader;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import static com.muah.muahbackend.domain.user.entity.UserRole.ROLE_INSTRUCTOR;
import static java.util.stream.Collectors.toCollection;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final UserRepository userRepository;

    private final S3Uploader s3Uploader;

    @Transactional(readOnly = true)
    public List<InstructorDto> getInstructorList(){
        List<Instructor> instructors = instructorRepository.findAll();
        ArrayList<InstructorDto> instructorList = instructors.stream().map(i -> new InstructorDto(i)).collect(toCollection(ArrayList::new));
        return instructorList;
    }

    @Transactional(readOnly = true)
    public InstructorDto getInstructorInfo(Long id){
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new InstructorNotFoundException());
        InstructorDto result = new InstructorDto(instructor);
        return result;
    }

    @Transactional
    public InstructorRegisterResponse createInstructorInfo(InstructorRegisterRequest request){
        Long id = request.getUserId();
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        user.setRole(ROLE_INSTRUCTOR);
        Instructor instructor = Instructor.builder()
                .instructor(user)
                .content(request.getContent())
                .build();

        return new InstructorRegisterResponse(instructorRepository.save(instructor).getId());

    }


    @Transactional
    public InstructorDto updateInstructorInfo(InstructorUpdateDto instructorInfo, Long id){
        return instructorRepository.findById(id)
                .map(instructor -> {
                    instructor.setContent(instructorInfo.getContext());
                    return new InstructorDto(instructorRepository.save(instructor));
                })
                .orElseThrow(() -> {
                    throw new InstructorNotFoundException();
                });
    }

    @Transactional
    public boolean deleteInstructor(Long id) throws IllegalAccessError{
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new InstructorNotFoundException());
        instructorRepository.delete(instructor);
        return true;
    }

    @Transactional
    public void uploadInstructorImage(MultipartFile uploadImage, Long id){
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new InstructorNotFoundException());

        Image prevImage = instructor.getImage();
        s3Uploader.deleteImage("instructor",prevImage);

        Image image = s3Uploader.uploadImage(uploadImage,"instructor");
        instructor.uploadImage(image);
        instructorRepository.save(instructor);

    }

    @Transactional
    public void deleteInstructorImage(Long id){
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new InstructorNotFoundException());

        //기존 지도사 이미지 삭제
        Image image = instructor.getImage();
        s3Uploader.deleteImage("instructor",image);

        instructorRepository.save(instructor);

    }



}
