package com.muah.muahbackend.domain.user.service;

import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import com.muah.muahbackend.global.vo.Image;
import com.muah.muahbackend.infra.aws.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserImageService {

    private final UserRepository userRepository;

    private final S3Uploader s3Uploader;

    @Transactional
    public void uploadUserImage(MultipartFile uploadImage, Long id){
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException());

        //기존 유저 사진 삭제
        Image prevImage = user.getImage();
        s3Uploader.deleteImage("user", prevImage);
        System.out.println("기존이미지 삭제 ");

        Image image = s3Uploader.uploadImage(uploadImage, "user");
        user.uploadImage(image);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserImage(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException());

        //기존 유저 사진 삭제
        Image image = user.getImage();
        s3Uploader.deleteImage("user", image);

        userRepository.save(user);
    }
}
