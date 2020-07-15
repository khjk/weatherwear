package com.kitri.weatherwear.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Wear {
    /*
    wear_no
    user_id
    wear_date
    temp_code
    wear_code
    like_no
    eval
    */

    int wearNo;
    int userId;
    Date wearDate;
    int tempCode;
    int wearCode;
    int likeNo;
    char eval;
}
