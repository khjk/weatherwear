package com.kitri.weatherwear.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String loc_latitude;
    private String loc_longitude;

    @Builder
    public UserUpdateRequestDto(String loc_latitude, String loc_longitude) {
        this.loc_latitude = loc_latitude;
        this.loc_longitude = loc_longitude;
    }
}
