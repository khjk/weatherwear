package com.kitri.weatherwear.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class UserSignUpRequestDto {
    @NotEmpty(message = "이름은 필수 입력값입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String id;

    @NotBlank(message = "패스워드는 필수 입력값입니다.")
    @Size(min=4,max=8,message="4~8자리 패스워드를 설정해주세요.")
    private String password;
    private String loc_latitude = "37.517235"; //default... Determine the point to update user's location
    private String loc_longitude = "127.047325"; //default...
    private String role = "ROLE_USER";

    @Builder
    public UserSignUpRequestDto(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    //OAUTH
    @Builder
    public UserSignUpRequestDto(String name, String email) {
        this.name = name;
        this.id = email;
    }
}
