package com.shun.controller.front;

import com.shun.entity.User;
import com.shun.service.UserService;
import com.shun.util.SecurityCode;
import com.shun.util.TextCodeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@RestController
@RequestMapping("c_user")
@Log4j2
public class C_UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("login")
    public Map login(User user) {
        return userService.doLogin(user);
    }
    @RequestMapping("register")
    public Map register(User user,String code) {
        return userService.doRegister(user,code);
    }
    @RequestMapping("sendCode")
    public Map sendCode(String phone) {
        Map map = new HashMap();
        try {
            String code = SecurityCode.getSecurityCode();
            boolean text = TextCodeUtil.sendCode(phone, code);
            if(!text){
                throw new RuntimeException("验证码发送失败");
            }
            stringRedisTemplate.opsForValue().set(phone, code);
            stringRedisTemplate.expire(phone, 5, TimeUnit.MINUTES);
            log.info(code);
            map.put("status", 200);
            map.put("message","短信发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", -200);
            map.put("message", "短信发送失败");
        }
        return map;

    }
    @RequestMapping("updateInfo")
    public Map updateInfo(User user) {
        return userService.doUpdateInfo(user);
    }
    @RequestMapping("getFriend")
    public Map getFriend(String uid) {
        return userService.getFriend(uid);
    }
}
