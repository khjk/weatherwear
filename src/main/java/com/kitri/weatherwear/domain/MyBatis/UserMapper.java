package com.kitri.weatherwear.domain.MyBatis;

import com.kitri.weatherwear.domain.User;
import com.kitri.weatherwear.web.dto.UserLoginRequestDto;
import com.kitri.weatherwear.web.dto.UserSignUpRequestDto;
import com.kitri.weatherwear.web.dto.UserUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface  UserMapper {
    List<User> findAll();
    Integer save(UserSignUpRequestDto userSignUpRequestDto);
    User findOne(String id);
    Integer deleteById(String id);
    Integer changeLocationById(@Param("id") String id,@Param("requestDto") UserUpdateRequestDto requestDto);
    User loginValidation(UserLoginRequestDto userLoginRequestDto);
}
