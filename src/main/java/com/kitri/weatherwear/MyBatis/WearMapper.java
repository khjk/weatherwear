package com.kitri.weatherwear.MyBatis;

import com.kitri.weatherwear.wear.Wear;
import com.kitri.weatherwear.wear.WearUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Mapper
public interface WearMapper {
    List<Wear> findAll();
    Integer save(Wear wear);
    Wear findOne(int wear_no);
    Integer updateEvaluationById(@Param("wear_no") int wear_no, @Param("requestDto") WearUpdateRequestDto requestDto);
    Integer deleteById(int wear_no);
    List<Date> findNotEvalDate(String user_id);
}
