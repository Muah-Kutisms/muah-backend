package com.muah.muahbackend.domain.user.dto;

import com.muah.muahbackend.domain.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @ApiModelProperty(value = "이메일", example = "aaa@gmail.com", required = true)
    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일의 형식이 맞지 않습니다")
    String email;

    public User convert() {
        return User.builder()
                .email(getEmail())
                .build();
    }


}
