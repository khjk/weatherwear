package com.kitri.weatherwear.MyBatis;

import com.kitri.weatherwear.user.User;
import com.kitri.weatherwear.user.UserUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();
    User save(User user);
    User findOne(String id);
    User deleteById(String id);
    User changeLocationById(String id, UserUpdateRequestDto requestDto);
}
