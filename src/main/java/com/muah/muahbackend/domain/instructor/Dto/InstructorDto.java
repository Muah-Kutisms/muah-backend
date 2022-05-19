package com.muah.muahbackend.domain.instructor.Dto;

import com.muah.muahbackend.domain.instructor.entity.Instructor;
import com.muah.muahbackend.global.vo.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructorDto {

    private Long UserId;
    private Long InstId;
    private String content;
    private Image image;

    public InstructorDto(Instructor instructor){
        this.UserId = instructor.getInstructor().getId();
        this.InstId = instructor.getId();
        this.content = instructor.getContent();
        this.image = instructor.getImage();
    }

}
