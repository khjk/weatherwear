package com.kitri.weatherwear.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;

@Slf4j
@RestController
public class UserApiController {
    private UserDaoService service;

    public UserApiController(UserDaoService service) { //의존성 주입
        this.service = service;
    }

    @GetMapping("api/v1/users/list")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @PostMapping("api/v1/validation")
    public User loginUser(@RequestBody UserLoginRequestDto userLoginRequestDto, HttpSession session) { //로그인
        User user = service.login(userLoginRequestDto);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s]의 로그인을 실패했습니다.", userLoginRequestDto.getId()));
        }else {
            session.setAttribute("id", user.getId());
        }
        return user;
    }

    @GetMapping("api/v1/users/{id}")
    public User retrieveUser(@PathVariable String id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s]가 존재하지 않습니다.", id));
        }
        return user;
    }

    @PostMapping("api/v1/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Integer savedResult = service.save(user);

        if(savedResult == 0) {
            throw new UserNotFoundException(String.format("ID[%s]를 생성할 수 없습니다.",user.getId()));
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("api/v1/users/{id}")
    public void deleteUser(@PathVariable String id) {
        Integer deletedResult = service.deleteById(id);

        if(deletedResult == 0) {
            throw new UserNotFoundException(String.format("ID[%s]를 삭제할 수 없습니다.", id));
        }
    }

    @PutMapping("api/v1/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserUpdateRequestDto requestDto) {
        Integer updatedResult = service.changeLocationById(id, requestDto);

        if(updatedResult == 0) {
            throw new UserNotFoundException(String.format("ID[%s]의 위치를 업데이트 할 수 없습니다.", id));
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
