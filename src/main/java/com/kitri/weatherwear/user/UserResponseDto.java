package com.kitri.weatherwear.user;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private String id;
    private String pwd;
    private String name;
    private String loc_latitude;
    private String loc_longitude;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.pwd = entity.getPwd();
        this.name = entity.getName();
        this.loc_latitude = entity.getLoc_latitude();
        this.loc_longitude = entity.getLoc_longitude();
    }
}
