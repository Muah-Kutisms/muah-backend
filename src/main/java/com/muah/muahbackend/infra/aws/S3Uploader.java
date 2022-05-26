package com.muah.muahbackend.infra.aws;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.muah.muahbackend.global.error.exception.CantConvertFileException;
import com.muah.muahbackend.global.util.ImageUtil;
import com.muah.muahbackend.global.vo.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    public String upload(MultipartFile multipartFile, String dirName, String UUID, String name, String type) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(CantConvertFileException::new);
        return upload(uploadFile, dirName, UUID, name, type);
    }

    // S3로 파일 업로드하기
    public Image uploadImage(MultipartFile multipartFile, String dirName) {
        try {
            final Image image = ImageUtil.convertMultipartToImage(multipartFile);
            System.out.println("이미지 url 만들기");
            final String url = upload(multipartFile, dirName, image.getImageUUID(),
                    image.getImageName(), image.getImageType().toString().toLowerCase());
            System.out.println("이미지에 url 세팅");
            image.setUrl(url);
            return image;
        } catch (IOException e) {
            System.out.println("원인" + e.getCause());
            System.out.println("io exception 메세지 : " + e.getMessage());
            throw new RuntimeException();
        }
    }

    public void delete(String dirName, String UUID, String name) {
        String filename = dirName + "/" + UUID + "_" + name;
        deleteS3(filename);
    }

    public void deleteImage(String dirName, Image image) {
        if (image.getImageUUID().equals("base-UUID")) {
            return;
        }
        String filename = dirName + "/" + image.getImageUUID() + "_" + image.getImageName()
                + "." + image.getImageType().toString();
        deleteS3(filename);
    }

    private String upload(File uploadFile, String dirName, String UUID, String name, String type) {
        String fileName = dirName + "/" + UUID + "_" + name + "." + type;
        System.out.println("s3에 업로드");
        String uploadImageUrl = putS3(uploadFile, fileName);

        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void deleteS3(String filename) {
        amazonS3Client.deleteObject(bucket, filename);
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            return;
        }
        log.info("File delete fail");
    }


    private Optional<File> convert(MultipartFile file) throws IOException {
        System.out.println("convert Multipart > File");
        File convertFile = new File(System.getProperty("user.dir") + "\\upload\\" + file.getOriginalFilename());
        System.out.println("루트 사용자에게 해당 파일 접근 권한 주기");
        Runtime.getRuntime().exec("chmod -R 777 " + convertFile);
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                System.out.println("파일 바이트 스트림으로 저장");
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

}
