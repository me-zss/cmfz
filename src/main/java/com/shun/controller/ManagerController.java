package com.shun.controller;

import com.shun.entity.Manager;
import com.shun.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("login")
    public Map<String,String> login(Manager manager,String checkCode, HttpServletRequest request){
        String key = (String) request.getSession().getAttribute("key");
        Map<String, String> result = new HashMap<>();
        if(key==null){
            result.put("status", "codeError");
            return result;
        }
        String s = stringRedisTemplate.opsForValue().get(key);
        if(s==null||!s.equals(checkCode)){
            result.put("status", "codeError");
            return result;
        }
        Manager reManager = managerService.login(manager);
        if (reManager == null) {
            result.put("status", "error");
        } else {
            request.getSession().setAttribute("manager", reManager);
            request.getSession().removeAttribute("key");
            result.put("status", "ok");
        }
        return result;
    }
    @RequestMapping("logout")
    public Map logout(HttpServletRequest request) {
        return managerService.logout(request);
    }
}
