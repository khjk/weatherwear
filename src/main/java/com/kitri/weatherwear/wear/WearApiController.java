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

    //MappingJacksonValue로 반환하고 Filter로 조작하는 것 고려해보기
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

        if (updateResult == 0) {
            throw new WearNotFoundException(String.format("Wear[%s] cannot update now", wear_no));
        }

        return updateResult;
    }

    @DeleteMapping("/{wear_no}")
    public void deleteWear(@PathVariable int wear_no) {
        Integer deleteResult = service.deleteById(wear_no);

        if (deleteResult == 0) {
            throw new WearNotFoundException(String.format("Wear_no[%s] cannot be deleted", wear_no));
        }
    }


    @GetMapping("/list/{user_id}")
    public List<String> retrievedDate(@PathVariable String user_id) {
        System.out.println("wears/list까진옴");
        List<String> dateList = new ArrayList<String>(){};

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for(Date d : service.findRegiterDate(user_id)){
            dateList.add(df.format(d));
            System.out.println(d);
        }
        return dateList;
    }

}
