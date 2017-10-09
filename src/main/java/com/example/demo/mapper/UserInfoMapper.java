package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.UserInfo;

@Mapper
public interface UserInfoMapper extends MyMapper<UserInfo>  {


}
