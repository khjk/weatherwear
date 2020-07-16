package com.kitri.weatherwear.service;

import com.kitri.weatherwear.dto.Clothes;

import java.util.List;
import java.util.Map;

public interface ClothesService {
    public List<Clothes> selectAllClothes();
    public List<Clothes> selectCsort(String sort);
    public int getWcode(int outer, int top, int bottom);
    public List<Clothes> selectGetWcode(int wearCode);
    public Clothes selectRain();
}
