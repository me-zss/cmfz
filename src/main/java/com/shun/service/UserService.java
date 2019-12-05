package com.shun.service;

import com.shun.entity.User;

import java.util.Map;

public interface UserService {
    Map findAll(Integer page,Integer rows);
    Map findByExample(String searchField,String searchString,String searchOper,Integer page,Integer rows);
    User findById(String id);
    Map modify(User user);
    Map findNewUsers();
    Map findUserCity();
    void addUser(User user);

    Map doLogin(User user);

    Map doRegister(User user, String code);

    Map doUpdateInfo(User user);

    Map getFriend(String uid);
}
