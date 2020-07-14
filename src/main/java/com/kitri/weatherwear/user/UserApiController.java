package com.kitri.weatherwear.user;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserApiController {
    private UserDaoService service;

    public UserApiController(UserDaoService service) { //의존성 주입
        this.service = service;
    }

    @GetMapping("api/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("api/users/{id}")
    public User retrieveUser(@PathVariable String id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return user;
    }

    //POST 201반환 component build클래스 사용
    @PostMapping("api/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = service.save(user);
                        //현재 요청의 URI 얻어서 path에 savedUser한 Id값을 넣어서 URI로 만들기
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("api/users/{id}")
    public void deleteUser(@PathVariable String id) {
        User deletedUser = service.deleteById(id);

        if(deletedUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] cannot delete", id));
        }
    }

    @PutMapping("api/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserUpdateRequestDto requestDto) {
        User updatedUser = service.changeLocationById(id, requestDto.getLoc_latitude(), requestDto.getLoc_longitude());

        if(updatedUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] cannot update location", id));
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(updatedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
