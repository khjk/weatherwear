package com.kitri.weatherwear.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@ApiModel(description = "사용자가 저장한 옷차림 목록과 평가")
public class Wear {
    @ApiModelProperty(notes="중복되지 않는 일련 번호")
    private Integer wear_no; //primary key
    @ApiModelProperty(notes="해당 옷차림을 입은 사용자의 아이디")
    private String user_id;
    @ApiModelProperty(notes="해당 옷차림을 입은 날짜")
    private LocalDate wear_date;
    @ApiModelProperty(notes="해당 옷차림을 조합한 코드 넘버")
    private Integer wear_code;
    @ApiModelProperty(notes="해당 옷차림을 입은 날씨의 코드 넘버")
    private Integer temp_code;
    @ApiModelProperty(notes="해당 옷차림에대한 적절성 평가")
    private Integer like_no;
    @ApiModelProperty(notes="해당 옷차림에대한 평가 여부")
    private Character eval;
}
