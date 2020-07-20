package com.kitri.weatherwear.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class User {
    private String id;
    private String password;
    private String name;
    private String loc_latitude;
    private String loc_longitude;
    private String user_role; //GUEST, USER
}
