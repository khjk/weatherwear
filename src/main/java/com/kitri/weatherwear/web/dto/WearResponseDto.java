package com.kitri.weatherwear.web.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class WearResponseDto {
    private Integer wear_no; //primary key
    private String user_id;
    private LocalDate wear_date;
    private Integer temp_code;
    private Integer wear_code;
    private Integer like_no;
    private Character eval;
}
