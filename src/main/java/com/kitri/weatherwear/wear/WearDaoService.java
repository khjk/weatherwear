package com.kitri.weatherwear.wear;

import com.kitri.weatherwear.MyBatis.WearMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    public Integer save(Wear wear) {
        return wearMapper.save(wear);
    }

    public Wear findOne(int wear_no) {
        return wearMapper.findOne(wear_no);
    }

    public Integer updateEvaluationById(int wear_no, WearUpdateRequestDto requestDto) {
        log.info("Service RequestDTO>>>>"+ wear_no + "like_no: " + requestDto.getLike_no() + " eval:" + requestDto.getEval()+"'");
        return wearMapper.updateEvaluationById(wear_no, requestDto);
    }

    public Integer deleteById(int wear_no) {
        return wearMapper.deleteById(wear_no);
    }

    public List<WearResponseDto> findNotEvaluated(String user_id) {
        log.info("WearDAOService >> ok");
        return wearMapper.findNotEvaluated(user_id);
    }

    public List<Date> findRegiterDate(String user_id) {
        return wearMapper.findRegiterDate(user_id);
    }

}