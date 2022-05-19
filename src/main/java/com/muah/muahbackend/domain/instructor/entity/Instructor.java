package com.muah.muahbackend.domain.instructor.entity;

import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.global.entity.Base;
import lombok.*;

import com.muah.muahbackend.global.vo.Image;
import com.muah.muahbackend.global.vo.ImageType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="instructors")
public class Instructor extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id")
    private Long id;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User instructor;

    @Column(name="instructor_content")
    @Lob
    private String content;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "imageUrl", column = @Column(name = "instructor_image_url")),
            @AttributeOverride(name = "imageType", column = @Column(name = "instructor_image_type")),
            @AttributeOverride(name = "imageName", column = @Column(name = "instructor_image_name")),
            @AttributeOverride(name = "imageUUID", column = @Column(name = "instructor_image_uuid"))
    })
    private Image image;


    public void uploadImage(Image image){
        deleteImage();
        this.image = image;
    }

    public void deleteImage() {
        if (this.image.getImageUUID().equals("base-UUID"))
            return;

        this.image = Image.builder()
                .imageName("base")
                .imageType(ImageType.PNG)
                .imageUrl("https://muah-bucket.s3.ap-northeast-2.amazonaws.com/instructor/base-UUID_base.png")
                .imageUUID("base-UUID")
                .build();
    }


    @Builder
    public Instructor(User instructor, String content) {
        this.instructor = instructor;
        this.content = content;
        this.image = Image.builder()
                .imageName("base")
                .imageType(ImageType.PNG)
                .imageUrl("https://muah-bucket.s3.ap-northeast-2.amazonaws.com/instructor/base-UUID_base.png")
                .imageUUID("base-UUID")
                .build();
    }
}
