package com.kitri.weatherwear.user;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

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
