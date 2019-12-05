package com.shun.dao;

import com.shun.entity.User;
import com.shun.entity.UserLocation;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserDao extends Mapper<User> {
    List<UserLocation> selectCityAndLocation();
}
