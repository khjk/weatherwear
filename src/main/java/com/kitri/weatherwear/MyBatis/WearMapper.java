package com.kitri.weatherwear.MyBatis;

import com.kitri.weatherwear.wear.Wear;
import com.kitri.weatherwear.wear.WearUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Iterator;
import java.util.List;

@Mapper
public interface WearMapper {
    List<Wear> findAll();
    int save(Wear wear);
    Wear findOne(int wear_no);
    int updateEvaluationById(int wear_no, WearUpdateRequestDto requestDto);
    int deleteById(int wear_no);
}
