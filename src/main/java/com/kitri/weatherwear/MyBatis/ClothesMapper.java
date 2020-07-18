package com.kitri.weatherwear.MyBatis;

import com.kitri.weatherwear.clothes.Clothes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClothesMapper {
    public List<Clothes> selectAllClothes();
    public List<Clothes> selectCsort(String sort);
    public int getWcode(int outer, int top, int bottom);
    public List<Clothes> selectGetWcode(int wearCode);
    public Clothes selectRain();
}
