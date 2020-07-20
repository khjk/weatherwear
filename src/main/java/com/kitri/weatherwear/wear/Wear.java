package com.kitri.weatherwear.wear;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Wear {
    private Integer wear_no; //primary key
    private String user_id;
    private Date wear_date;
    private Integer wear_code;
    private Integer temp_code;
    private Integer like_no;
    private Character eval;
}
