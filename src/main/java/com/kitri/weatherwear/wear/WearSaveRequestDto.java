package com.kitri.weatherwear.wear;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
public class WearSaveRequestDto {
    String user_id;
    LocalDate wear_date;
    int temp_code;
    int wear_code;
    int like_no = 0;
    char eval = 'N';
}
