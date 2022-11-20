package com.kitri.weatherwear.domain.MyBatis;

import com.kitri.weatherwear.domain.Clothes;
import com.kitri.weatherwear.domain.ClothesNameList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClothesMapper {
    public List<Clothes> selectAllClothes();
    public List<Clothes> selectCsort(String sort);
    public int getWcode(int outer, int top, int bottom);
    public List<Clothes> selectGetWcode(int wearCode);
    public Clothes selectRain();
    public ClothesNameList getClothesNameByCode(int wCode);
    public List<String> selectThreeImageByNameList(ClothesNameList nameList);
}
