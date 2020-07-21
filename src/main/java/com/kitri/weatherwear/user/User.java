package com.kitri.weatherwear.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value={"password"})
@ApiModel(description = "사용자 위치를 포함한 가입 정보")
public class User {
    @ApiModelProperty(notes="이메일 형식의 아이디")
    private String id;
    @ApiModelProperty(notes = "공개금지")
    private String password;
    @ApiModelProperty(notes="사용자 이름")
    private String name;
    @ApiModelProperty(notes="위도")
    private String loc_latitude;
    @ApiModelProperty(notes="경도")
    private String loc_longitude;
    @ApiModelProperty(notes="SpringSecurity를 위한 Column..아직 사용되지 않음")
    private String user_role; //ROLE_GUEST, ROLE_USER

    @Builder
    public User(String name, String email) {
        this.name = name;
        this.id = email;
    }
}
