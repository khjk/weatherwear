package com.kitri.weatherwear.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
public class UserApiController {
    private UserDaoService service;

    public UserApiController(UserDaoService service) { //의존성 주입
        this.service = service;
    }

    @GetMapping("/list")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @PostMapping("/validation")
    public User loginUser(@RequestBody UserLoginRequestDto userLoginRequestDto, HttpSession session) { //로그인
        User user = service.login(userLoginRequestDto);
        System.out.println(">>>validation: " + user.getId());
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s]의 로그인을 실패했습니다.", userLoginRequestDto.getId()));
        }else {

            session.setAttribute("id", user.getId());

            session.setAttribute("user_id", user.getId());

        }
        return user;
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable String id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s]가 존재하지 않습니다.", id));
        }
        return user;
    }

    @PostMapping("")
    public int createUser(@RequestBody UserSignUpRequestDto userSignUpRequestDto, HttpSession session) {
        Integer savedResult = service.save(userSignUpRequestDto);
        System.out.println(">>>회원가입:"+userSignUpRequestDto.getId());
        if(savedResult == 0) {
            throw new UserNotFoundException(String.format("ID[%s]를 생성할 수 없습니다.",userSignUpRequestDto.getId()));
        }else {
            session.setAttribute("id",userSignUpRequestDto.getId());
            System.out.println(">>>>apiController user_id:" + userSignUpRequestDto.getId() +"세션으로 저장");
        }

        return savedResult;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        Integer deletedResult = service.deleteById(id);

        if(deletedResult == 0) {
            throw new UserNotFoundException(String.format("ID[%s]를 삭제할 수 없습니다.", id));
        }
    }

    @PutMapping("/{id}")
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

    @GetMapping("/id-check/{id}")
    public int idCheck(@PathVariable String id) {
        return service.userIdCheck(id);
    }

}
