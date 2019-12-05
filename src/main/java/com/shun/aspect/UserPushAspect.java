package com.shun.aspect;

import com.alibaba.fastjson.JSON;
import com.shun.service.UserService;
import io.goeasy.GoEasy;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Aspect
@Configuration
@Slf4j
public class UserPushAspect {
    @Autowired
    private UserService userService;
    @After("@annotation(com.shun.annotation.UserUpdateAnnotation)")
    public void changeLocationPush(JoinPoint joinPoint) throws Throwable {
        Map userCity = userService.findUserCity();
        String cities = JSON.toJSONString(userCity.get("cities"));
        log.info(cities);
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io","BC-fb7661a2d2ac4e84a56093683db351c5");
        goEasy.publish("cities", cities);
    }
    @After("@annotation(com.shun.annotation.UserAddAnnotation)")
    public void addUserPush(JoinPoint joinPoint) throws Throwable {
        Map map = userService.findNewUsers();
        String newRegister = JSON.toJSONString(map);
        log.info(newRegister);
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io","BC-fb7661a2d2ac4e84a56093683db351c5");
        goEasy.publish("registers", newRegister);
    }
}