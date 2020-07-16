package com.kitri.weatherwear.dao;

import com.kitri.weatherwear.dto.Clothes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ClothesDao {
    public List<Clothes> selectAllClothes();
    public List<Clothes> selectCsort(String sort);
    public int getWcode(int outer, int top, int bottom);
    public List<Clothes> selectGetWcode(int wearCode);
    public String selectRainImg();
}
