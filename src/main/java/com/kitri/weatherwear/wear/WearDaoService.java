package com.kitri.weatherwear.wear;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class WearDaoService {
    private static List<Wear> wears = new ArrayList<>();

    private static int wearsCount = 4;
    static {
        wears.add(new Wear(1,"begywjd",new Date(),3,4,0,'N'));
        wears.add(new Wear(2,"begywjd",new Date(),2,4,0,'N'));
        wears.add(new Wear(3,"begywjd",new Date(),2,4,0,'N'));
        wears.add(new Wear(4,"begywjd",new Date(),3,4,0,'N'));
    }

    public List<Wear> findAll() {
        for (Wear wear : wears) { //testCode
            log.info("Service FindALL>>>>>>>>" + wear.getWear_no() + wear.getEval());
        }
        return wears;
    }

    public Wear save(Wear wear) {
        if (wear.getWear_no() == null) {
            wear.setWear_no(++wearsCount);
        }

        wears.add(wear);
        return wear;
    }

    public Wear findOne(int wear_no) {
        for (Wear wear : wears) {
            if(wear.getWear_no() == wear_no) {
                return wear;
            }
        }
        return null;
    }

    public Wear updateEvaluationById(int wear_no, WearUpdateRequestDto requestDto) { //꼭 WearUpdateRequestDto로 해야될지 생각해보기... 필요없을지도.. 걍 스트링넘겨주면되는데... 나중에 확장생각해서
        log.info("Service RequestDTO>>>>"+ wear_no + "like_no: " + requestDto.getLike_no() + "eval:" + requestDto.getEval()+"'");
        for (Wear wear : wears) {
            if (wear.getWear_no() == wear_no) {
                wear.setLike_no(requestDto.getLike_no());
                wear.setEval(requestDto.getEval());
                log.info("Service Evaluation>>>>>" + wear.getWear_no() + "like_no :" + wear.getLike_no() + "eval:" + wear.getEval()+"'");
                return wear;
            }
        }
        return null;
    }

    public Wear deleteById(int wear_no) {
        Iterator<Wear> iterator = wears.iterator();
        while (iterator.hasNext()) {
            Wear wear = iterator.next();

            if(wear.getWear_no() == wear_no) {
                iterator.remove();
                return wear;
            }
        }
        return null;
    }

}