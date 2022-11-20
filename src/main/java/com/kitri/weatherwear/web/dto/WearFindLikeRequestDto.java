package com.kitri.weatherwear.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WearFindLikeRequestDto {
    String user_id;
    int temp_code;

    @Builder
    public WearFindLikeRequestDto(String user_id, int temp_code) {
        this.user_id = user_id;
        this.temp_code = temp_code;
    }
}
