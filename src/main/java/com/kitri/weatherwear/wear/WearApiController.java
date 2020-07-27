package com.kitri.weatherwear.wear;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/wears")
public class WearApiController {
    private final WearDaoService service;

    public WearApiController(WearDaoService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public List<Wear> retrieveAllWears() {
        return service.findAll();
    }

    @GetMapping("/{wear_no}")
    public Wear retrieveWear(@PathVariable int wear_no) {
        Wear wear = service.findOne(wear_no);

        if(wear == null){
            throw new WearNotFoundException(String.format("Wear_No[%s] not found", wear_no));
        }
        return wear;
    }

    @GetMapping("/{user_id}/no-eval")
    public List<WearResponseDto> retrieveUnEvaluated(@PathVariable String user_id) {
        return service.findNotEvaluated(user_id);
    }

    @PostMapping("")
    public int saveWear(@RequestBody WearSaveRequestDto wearSaveRequestDto) {
        Integer saveResult = service.save(wearSaveRequestDto);

        if (saveResult == 0) {
            throw new WearNotFoundException("Wear[%s] cannot be saved..");
        }

        return saveResult;
    }

    @PutMapping("/{wear_no}")
    public int evaluateWear(@PathVariable int wear_no, @RequestBody WearUpdateRequestDto requestDto) {
        Integer updateResult = service.updateEvaluationById(wear_no, requestDto);
        System.out.println("updateReulst:" + updateResult + "wear_no" + wear_no + "RequestDto:" + requestDto.getEval() + requestDto.getLike_no());
        if (updateResult == 0) {
            throw new WearNotFoundException(String.format("Wear[%s] cannot update now", wear_no));
        }

        return updateResult;
    }

    @DeleteMapping("/{wear_no}")
    public int deleteWear(@PathVariable int wear_no) {
        Integer deleteResult = service.deleteById(wear_no);
        System.out.println("지움:" + wear_no);
        if (deleteResult == 0) {
            throw new WearNotFoundException(String.format("Wear_no[%s] cannot be deleted", wear_no));
        }
        return deleteResult;
    }


    @GetMapping("/list/{user_id}")
    public List<String> retrievedDate(@PathVariable String user_id) {
        List<String> dateList = new ArrayList<String>(){};

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for(Date d : service.findRegiterDate(user_id)){
            dateList.add(df.format(d));
            System.out.println(d);
        }
        return dateList;
    }

    @PostMapping("/best-like")
    public String getWearCodeByBestLikeByTempCode(@RequestBody WearFindLikeRequestDto wearFindLikeRequestDto) {
        String BestLike = service.getBestLikeByTempCode(wearFindLikeRequestDto);
        String BestWearCode = "1"; //default
        if(BestLike == null ) {
            System.out.println("오늘에 날씨 코드에 해당하는 유저데이터가 없습니다.");
            switch(wearFindLikeRequestDto.getTemp_code()){
                case 1 : //28도 이상
                    BestWearCode = "177";
                    break;
                case 2 : //23도 이상
                    BestWearCode = "178";
                    break;
                case 3 : //20도 이상
                    BestWearCode = "186";
                    break;
                case 4 : //17도 이상
                    BestWearCode = "7";
                    break;
                case 5 : //12도 이상
                    BestWearCode = "18";
                    break;
                case 6 : //9도이상
                    BestWearCode = "50";
                    break;
                case 7 : //5도이상
                    BestWearCode = "74";
                    break;
                case 8: //5도미만
                    BestWearCode = "150";
                    break;
                default : //이상한 Temp_CODE 요청시
                    BestWearCode = "193";
                    break;
            }
        }
        String result = service.getBestWearCodeByBestLike(BestLike, wearFindLikeRequestDto);
        if(result == null) {
            System.out.println(">>>getBestWearCode API DEFAULT BEST_WEAR_CODE:" + BestWearCode);
            return BestWearCode;
        } else {
            return result;
        }
    }

    @DeleteMapping("/data/{user_id}")
    public int deleteAllByUserID(@PathVariable String user_id) {
        Integer deleteResult = service.deleteAllById(user_id);
        System.out.println("지움:" + user_id);
        if (deleteResult == 0) {
            throw new WearNotFoundException(String.format("UserID[%s]'s Wear Data cannot be deleted", user_id));
        }
        return deleteResult;
    }

}
