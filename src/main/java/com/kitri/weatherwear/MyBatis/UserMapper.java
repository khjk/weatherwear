package com.kitri.weatherwear.MyBatis;

import com.kitri.weatherwear.user.User;
import com.kitri.weatherwear.user.UserLoginRequestDto;
import com.kitri.weatherwear.user.UserSignUpRequestDto;
import com.kitri.weatherwear.user.UserUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
<<<<<<< HEAD
public interface UserMapper {
=======
public interface  UserMapper {
>>>>>>> origin/develop
    List<User> findAll();
    Integer save(UserSignUpRequestDto userSignUpRequestDto);
    User findOne(String id);
    Integer deleteById(String id);
    Integer changeLocationById(@Param("id") String id,@Param("requestDto") UserUpdateRequestDto requestDto);
    User loginValidation(UserLoginRequestDto userLoginRequestDto);
<<<<<<< HEAD
    int userIdCheck(String id);
=======
>>>>>>> origin/develop
}
