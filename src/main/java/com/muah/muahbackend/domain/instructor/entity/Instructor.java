package com.muah.muahbackend.domain.instructor.entity;

import com.muah.muahbackend.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="instructors")
public class Instructor {

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

    // TODO : 이미지 필드

    @Builder
    public Instructor(User instructor, String content) {
        this.instructor = instructor;
        this.content = content;
    }
}
