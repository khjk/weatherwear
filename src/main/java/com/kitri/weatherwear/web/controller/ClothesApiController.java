package com.kitri.weatherwear.web.controller;


import com.kitri.weatherwear.service.ClothesDaoService;
import com.kitri.weatherwear.domain.ClothesNameList;
import com.kitri.weatherwear.domain.Clothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClothesApiController {

    @Autowired
    ClothesDaoService cService;

    /*
    Clothes_sort 전체 테이블 불러오기
    */
    @GetMapping("api/v1/clothes/list")
    public List<Clothes> getAllClothes(){
        List<Clothes> clotheslist = cService.selectAllClothes();
        return clotheslist;
    }

    /*
    옷 종류별로 불러오기
    {c_sort} - String: 'outer', 'top', 'bottom'
    예) api/v1/clothes/outer
     */
    @GetMapping("api/v1/clothes/{c_sort}")
    public List<Clothes> getSortClothes(@PathVariable String c_sort){
        List<Clothes> sortlist = cService.selectCsort(c_sort);
        return sortlist;
    }

    /*
    wear-code 불러오기
    outer, top, bottom 이 null인 경우 param = 0 으로 셋팅 해주어야함
    예)원피스 - ..../0/9/0
    */
    @GetMapping("api/v1/clothes/{outer}/{top}/{bottom}")
    public int getWearCode(@PathVariable int outer, @PathVariable int top, @PathVariable int bottom){
        int wearCode = cService.getWcode(outer, top, bottom);
        return wearCode;
    }

    /*
    wear-code에 따른 clothes 객체 리스트 불러오기
    {wCode} - int : wear_code 값
     */
    @GetMapping("api/v1/clothes/wear/{wCode}")
    public List<Clothes> getClothes(@PathVariable int wCode){
        List<Clothes> wlist = cService.selectGetWcode(wCode);
        return wlist;
    }

    /*
    비 올때 우산 clothes 객체 불러오기
     */
    @GetMapping("api/v1/clothes/rain")
    public Clothes getRain(){
        return cService.selectRain();
    }

    /*
    wear-code에 따른 clothes 이름 리스트 불러오고,
    그 이름 리스트로 이미지 리스트 세개 반환하기
    */
    @GetMapping("api/v1/clothes/img/{wCode}")
    public List<String> getThreeImage(@PathVariable int wCode){
        ClothesNameList nameList = cService.getClothesNameByCode(wCode);
        System.out.println(nameList.getBottom_wear() + nameList.getTop_wear() + nameList.getOuter_wear());
        return cService.selectThreeImage(nameList);
    }
}
