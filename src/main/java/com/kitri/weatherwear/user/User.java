package com.kitri.weatherwear.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private String id;
    private String pwd;
    private String name;
    private String loc_latitude;
    private String loc_longitude;
}
