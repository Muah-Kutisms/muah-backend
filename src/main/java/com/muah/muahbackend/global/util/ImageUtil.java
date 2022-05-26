package com.muah.muahbackend.global.util;

import com.google.common.base.Enums;
import com.muah.muahbackend.global.error.exception.NotSupportImageTypeException;
import com.muah.muahbackend.global.vo.Image;
import com.muah.muahbackend.global.vo.ImageType;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class ImageUtil {

    public static Image convertMultipartToImage(MultipartFile file){

        String originalName = file.getOriginalFilename();
        String name = FilenameUtils.getBaseName(originalName);
        String type = FilenameUtils.getExtension(originalName).toUpperCase();
        if(!Enums.getIfPresent(ImageType.class, type).isPresent())
            throw new NotSupportImageTypeException();
        Image image = Image.builder()
                .imageType(ImageType.valueOf(type))
                .imageName(name)
                .imageUUID(UUID.randomUUID().toString())
                .build();

        return image;
    }

    public static Image getBaseImage(){
        return Image.builder()
                .imageName("base")
                .imageType(ImageType.JPG)
                .imageUrl("https://muah-bucket.s3.ap-northeast-2.amazonaws.com/pet/base-UUID_base.jpg")
                .imageUUID("base-UUID")
                .build();
    }

    public static Image getBaseInstructorImage(){
        return Image.builder()
                .imageName("base")
                .imageType(ImageType.PNG)
                .imageUrl("https://muah-bucket.s3.ap-northeast-2.amazonaws.com/instructor/base-UUID_base.png")
                .imageUUID("base-UUID")
                .build();
    }
}
