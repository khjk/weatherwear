package com.kitri.weatherwear.user;

import com.kitri.weatherwear.MyBatis.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class UserDaoService {

    @Autowired
    UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public int save(UserSignUpRequestDto userSignUpRequestDto) {
        return userMapper.save(userSignUpRequestDto);
    }

    public User findOne(String id) {
        return userMapper.findOne(id);
    }

    public User login(UserLoginRequestDto userLoginRequestDto) {
        //log.info("Service RequestDTO>>>>"+ userLoginRequestDto.getId() + userLoginRequestDto.getPassword() );
        return userMapper.loginValidation(userLoginRequestDto);
    }

    public int deleteById(String id) {
        return userMapper.deleteById(id);
    }

    public int changeLocationById(String id, UserUpdateRequestDto requestDto) {
        return userMapper.changeLocationById(id, requestDto);
    }

    public int userIdCheck(String id) {
        return userMapper.userIdCheck(id);
    }
}
