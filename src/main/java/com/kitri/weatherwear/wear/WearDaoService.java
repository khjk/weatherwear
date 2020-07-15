package com.kitri.weatherwear.wear;

import com.kitri.weatherwear.MyBatis.WearMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@Transactional
public class WearDaoService {

    @Autowired
    WearMapper wearMapper;

    public List<Wear> findAll() {
        return wearMapper.findAll();
    }

    public Wear save(Wear wear) {
        wearMapper.save(wear);
        return wear;
    }

    public Wear findOne(int wear_no) {
        wearMapper.findOne(wear_no);
        return null;
    }

    public Wear updateEvaluationById(int wear_no, WearUpdateRequestDto requestDto) { //꼭 WearUpdateRequestDto로 해야될지 생각해보기... 필요없을지도.. 걍 스트링넘겨주면되는데... 나중에 확장생각해서
        log.info("Service RequestDTO>>>>"+ wear_no + "like_no: " + requestDto.getLike_no() + "eval:" + requestDto.getEval()+"'");
        return wearMapper.updateEvaluationById(wear_no, requestDto);
    }

    public Wear deleteById(int wear_no) {
        return wearMapper.deleteById(wear_no);
    }

}