package com.kitri.weatherwear.user;

import com.kitri.weatherwear.MyBatis.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserDaoService {

    @Autowired
    UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public User save(User user) {
        userMapper.save(user);
        return user;
    }

    public UserResponseDto findOne(String id) {
        User entity = userMapper.findOne(id);
        return new UserResponseDto(entity);
    }

    public User deleteById(String id) {
        return userMapper.deleteById(id);
    }

    public User changeLocationById(String id, UserUpdateRequestDto requestDto) {
        return userMapper.changeLocationById(id, requestDto);
    }
}
