package com.kitri.weatherwear.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSignUpRequestDto {
    private String name;
    private String id;
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


