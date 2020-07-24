package com.kitri.weatherwear.MyBatis;

import com.kitri.weatherwear.wear.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface WearMapper {
    List<Wear> findAll();
    Integer save(WearSaveRequestDto wearSaveRequestDto);
    Wear findOne(int wear_no);
    Integer updateEvaluationById(@Param("wear_no") int wear_no, @Param("requestDto") WearUpdateRequestDto requestDto);
    Integer deleteById(int wear_no);
    List<WearResponseDto> findNotEvaluated(String user_id);
    List<Date> findRegiterDate(String user_id);
    String getBestLikeByTempCode(WearFindLikeRequestDto wearFindLikeRequestDto);
    String getBestWearCodeByBestLike(@Param("bestLike") String bestLike, @Param("requestDto") WearFindLikeRequestDto requestDto);
    List<WearResponseDto> findEvaluated(String user_id);
}

