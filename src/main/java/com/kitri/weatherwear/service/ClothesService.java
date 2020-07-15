package com.kitri.weatherwear.service;

import com.kitri.weatherwear.dto.Clothes;

import java.util.List;
import java.util.Map;

public interface ClothesService {
    public List<Clothes> selectClothes();
    public Map<String, String> selectOuter();
}
