package com.muah.muahbackend.domain.instructor.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructorRegisterRequest {

    private Long userId;
    private String content;


}
