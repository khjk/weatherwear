package com.kitri.weatherwear.service;

import com.kitri.weatherwear.domain.MyBatis.ClothesMapper;
import com.kitri.weatherwear.domain.ClothesNameList;
import com.kitri.weatherwear.domain.Clothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClothesDaoService {

    @Autowired
    ClothesMapper clothesMapper;

    public List<Clothes> selectAllClothes() {
        return clothesMapper.selectAllClothes();
    }

    public List<Clothes> selectCsort(String sort) {
        return clothesMapper.selectCsort(sort);
    }

    public int getWcode(int outer, int top, int bottom) {
        return clothesMapper.getWcode(outer, top, bottom);
    }

    public List<Clothes> selectGetWcode(int wearCode) {
        return clothesMapper.selectGetWcode(wearCode);
    }

    public Clothes selectRain() { return clothesMapper.selectRain();}

    public ClothesNameList getClothesNameByCode(int wCode) {
        return clothesMapper.getClothesNameByCode(wCode);
    }

    public List<String> selectThreeImage(ClothesNameList nameList){
        return clothesMapper.selectThreeImageByNameList(nameList);
    }
}
