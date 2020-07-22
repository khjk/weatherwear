package com.kitri.weatherwear.wear;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WearUpdateRequestDto {
    private int like_no;
    private char eval = 'Y';

    @Builder
    public WearUpdateRequestDto(int like_no) {
        this.like_no = like_no;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/develop
