package com.kitri.weatherwear.user;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserLoginRequestDto {

    private String id;

    private String password;

    @Builder
    public UserLoginRequestDto(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
