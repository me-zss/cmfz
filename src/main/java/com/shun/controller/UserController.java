package com.shun.controller;

import com.shun.entity.User;
import com.shun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("findAll")
    public Map findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer page,Integer rows) {
        if(_search){
            return userService.findByExample(searchField,searchString,searchOper,page,rows);
        }else {
            return userService.findAll(page, rows);
        }
    }
    @RequestMapping("changeStatus")
    public Map changeStatus(User user) {
        return userService.modify(user);
    }
    @RequestMapping("findNewUsers")
    public Map findNewUsers() {
        return userService.findNewUsers();
    }

    @RequestMapping("findUserLocations")
    public Map findUserLocations() {
        return userService.findUserCity();
    }
}
