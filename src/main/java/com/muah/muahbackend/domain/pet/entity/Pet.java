package com.muah.muahbackend.domain.pet.entity;

import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.global.entity.Base;
import com.muah.muahbackend.global.vo.Image;
import com.muah.muahbackend.global.vo.ImageType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="pets")
public class Pet extends Base {
    @Id
    @Column(name = "pet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name="pet_name")
    private String name;

    @Column(name="pet_kind")
    private String kind;

//    @Column(name="pet_gender")
//    @Enumerated(EnumType.STRING)
//    private Gender gender;

    @Column(name="pet_weight", scale = 1)
    private BigDecimal weight;

    @Column(name="pet_birthdate")
    private LocalDate birthdate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "imageUrl", column = @Column(name = "member_image_url")),
            @AttributeOverride(name = "imageType", column = @Column(name = "member_image_type")),
            @AttributeOverride(name = "imageName", column = @Column(name = "member_image_name")),
            @AttributeOverride(name = "imageUUID", column = @Column(name = "member_image_uuid"))
    })
    private Image image;

    @OneToMany(mappedBy = "pet")
    private List<Sheet> sheets = new ArrayList<>();

    public void uploadImage(Image image) {
        deleteImage();
        this.image = image;
    }

    public void deleteImage() {
        if (this.image.getImageUUID().equals("base-UUID"))
            return;

        this.image = Image.builder()
                .imageName("base")
                .imageType(ImageType.JPG)
                .imageUrl("https://muah-bucket.s3.ap-northeast-2.amazonaws.com/pet/base-UUID_base.jpg")
                .imageUUID("base-UUID")
                .build();
    }

    @Builder
    public Pet(User owner, String name, Gender gender, BigDecimal weight, LocalDate birthdate, String kind){
        this.owner = owner;
        this.name = name;
       // this.gender = gender;
        this.weight = weight;
        this.birthdate = birthdate;
        this.kind = kind;

        this.image = Image.builder()
                .imageName("base")
                .imageType(ImageType.JPG)
                .imageUrl("https://muah-bucket.s3.ap-northeast-2.amazonaws.com/pet/base-UUID_base.jpg")
                .imageUUID("base-UUID")
                .build();
    }

}