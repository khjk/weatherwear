package com.kitri.weatherwear.service;

import com.kitri.weatherwear.dao.ClothesDao;
import com.kitri.weatherwear.dto.Clothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothesServiceImpl implements ClothesService{

    @Autowired
    ClothesDao cDao;

    @Override
    public List<Clothes> selectAllClothes() {
        return cDao.selectAllClothes();
    }

    @Override
    public List<Clothes> selectCsort(String sort) {
        return cDao.selectCsort(sort);
    }

    @Override
    public int getWcode(int outer, int top, int bottom) {
        return cDao.getWcode(outer, top, bottom);
    }

    @Override
    public List<Clothes> selectGetWcode(int wearCode) {
        return cDao.selectGetWcode(wearCode);
    }

    @Override
    public Clothes selectRain() {
        return cDao.selectRain();
    }


}
