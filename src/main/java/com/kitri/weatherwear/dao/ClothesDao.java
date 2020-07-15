package com.kitri.weatherwear.dao;

import com.kitri.weatherwear.dto.Clothes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClothesDao {
    public List<Clothes> selectClothes();
    public Map<String, String> selectOuter();
}
