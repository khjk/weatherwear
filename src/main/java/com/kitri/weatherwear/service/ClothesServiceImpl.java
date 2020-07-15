package com.kitri.weatherwear.service;

import com.kitri.weatherwear.dao.ClothesDao;
import com.kitri.weatherwear.dto.Clothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClothesServiceImpl implements ClothesService{

    @Autowired
    ClothesDao cDao;

    @Override
    public List<Clothes> selectClothes() {
        return cDao.selectClothes();
    }

    @Override
    public Map<String, String> selectOuter() {
        return cDao.selectOuter();
    }


}
