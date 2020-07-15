package com.kitri.weatherwear.wear;

import com.kitri.weatherwear.user.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class WearResponseDto {
    private Integer wear_no; //primary key
    private String user_id;
    private Date wear_date;
    private Integer wear_code;
    private Integer temp_code;
    private Integer like_no;
    private Character eval;

    public WearResponseDto(Wear entity) {
        this.wear_no = entity.getWear_no();
        this.user_id = entity.getUser_id();
        this.wear_date = entity.getWear_date();
        this.wear_code = entity.getWear_code();
        this.temp_code = entity.getTemp_code();
        this.like_no = entity.getLike_no();
        this.eval = entity.getEval();
    }
}
