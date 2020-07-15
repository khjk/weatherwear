package com.kitri.weatherwear.wear;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class WearApiController {
    private WearDaoService service;

    public WearApiController(WearDaoService service) {
        this.service = service;
    }

    @GetMapping("api/wears")
    public List<Wear> retrieveAllWears() {
        return service.findAll();
    }

    @GetMapping("api/wears/{wear_no}")
    public Wear retrieveWear(@PathVariable int wear_no) {
        Wear wear = service.findOne(wear_no);

        if(wear == null){
            throw new WearNotFoundException(String.format("Wear_No[%s] not found", wear_no));
        }
        return wear;
    }

    @PostMapping("api/wears")
    public ResponseEntity<Wear> saveWear(@RequestBody Wear wear) {
        Wear savedWear = service.save(wear);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{wear_id}")
                .buildAndExpand(savedWear.getWear_no())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("api/wears/{wear_no}")
    public ResponseEntity<Wear> evaluateWear(@PathVariable int wear_no, @RequestBody WearUpdateRequestDto requestDto) {
        Wear updatedWear = service.updateEvaluationById(wear_no, requestDto);

        if(updatedWear == null) {
            throw new WearNotFoundException(String.format("Wear[%s] cannot update now", wear_no));
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(updatedWear.getWear_no())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("api/wears/{wear_no}")
    public void deleteWear(@PathVariable int wear_no) {
        Wear deletedWear = service.deleteById(wear_no);

        if(deletedWear == null) {
            throw new WearNotFoundException(String.format("Wear_no[%s] cannot be deleted", wear_no));
        }
    }

}
