package com.kitri.weatherwear.message;

import lombok.*;

import java.util.List;

/*
* Beta Version...
* */

@Data
@AllArgsConstructor
public class Message {
    private int temp_code;
    private List<String> message;
}
