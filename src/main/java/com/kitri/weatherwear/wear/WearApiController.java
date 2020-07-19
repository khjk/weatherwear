package com.kitri.weatherwear.wear;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
public class WearApiController {
    private final WearDaoService service;

    public WearApiController(WearDaoService service) {
        this.service = service;
    }

    @GetMapping("api/v1/wears/list")
    public List<Wear> retrieveAllWears() {
        return service.findAll();
    }

    @GetMapping("api/v1/wears/{wear_no}")
    public Wear retrieveWear(@PathVariable int wear_no) {
        Wear wear = service.findOne(wear_no);

        if(wear == null){
            throw new WearNotFoundException(String.format("Wear_No[%s] not found", wear_no));
        }
        return wear;
    }
    /*
    * 언니가 요청한 API
    * 요청방식 : GET
    * 필요한 요청인자 : user_id
    * 반환값 : User_id가 평가하지 않은 날짜 List
    * */
    @GetMapping("api/v1/wears/list/{user_id}/no-eval")
    public List<Date> retrieveNoEvaluatedDate(@PathVariable String user_id) {
        return service.findNotEvaluatedDate(user_id);
    }

    @PostMapping("api/v1/wears")
    public ResponseEntity<Wear> saveWear(@RequestBody Wear wear) {
        Integer saveResult = service.save(wear);

        if (saveResult == 0) {
            throw new WearNotFoundException(String.format("Wear[%s] cannot be saved..",wear.getWear_code()));
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{wear_id}")
                .buildAndExpand(wear.getWear_no())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("api/v1/wears/{wear_no}")
    public ResponseEntity<Wear> evaluateWear(@PathVariable int wear_no, @RequestBody WearUpdateRequestDto requestDto) {
        Integer updateResult = service.updateEvaluationById(wear_no, requestDto);

        if (updateResult == 0) {
            throw new WearNotFoundException(String.format("Wear[%s] cannot update now", wear_no));
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(wear_no)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("api/v1/wears/{wear_no}")
    public void deleteWear(@PathVariable int wear_no) {
        Integer deleteResult = service.deleteById(wear_no);

        if (deleteResult == 0) {
            throw new WearNotFoundException(String.format("Wear_no[%s] cannot be deleted", wear_no));
        }
    }

}
