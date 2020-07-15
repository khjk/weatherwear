package com.kitri.weatherwear.MyBatis;

import com.kitri.weatherwear.user.User;
import com.kitri.weatherwear.user.UserUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();
    Integer save(User user);
    User findOne(String id);
    Integer deleteById(String id);
    Integer changeLocationById(@Param("id") String id,@Param("requestDto") UserUpdateRequestDto requestDto);
}
