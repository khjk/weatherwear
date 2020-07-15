package com.kitri.weatherwear.wear;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping("api/v1/wears")
    public ResponseEntity<Wear> saveWear(@RequestBody Wear wear) {
        int saveResult = service.save(wear);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{wear_id}")
                .buildAndExpand(wear.getWear_no())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("api/v1/wears/{wear_no}")
    public ResponseEntity<Wear> evaluateWear(@PathVariable int wear_no, @RequestBody WearUpdateRequestDto requestDto) {
        int updateResult = service.updateEvaluationById(wear_no, requestDto);

        if(updateResult == 0) {
            throw new WearNotFoundException(String.format("Wear[%s] cannot update now", wear_no));
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(wear_no)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("api/v1/wears/{wear_no}")
    public void deleteWear(@PathVariable int wear_no) {
        int deleteResult = service.deleteById(wear_no);

        if(deleteResult == 0) {
            throw new WearNotFoundException(String.format("Wear_no[%s] cannot be deleted", wear_no));
        }
    }

}
