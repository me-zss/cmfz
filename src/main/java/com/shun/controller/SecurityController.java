package com.shun.controller;

import com.shun.util.SecurityCode;
import com.shun.util.SecurityImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("security")
public class SecurityController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String code = SecurityCode.getSecurityCode();
        String key = (String) request.getSession().getAttribute("key");
        key = key==null? UUID.randomUUID().toString():key;
        request.getSession().setAttribute("key",key);
        stringRedisTemplate.opsForValue().set(key,code);
        stringRedisTemplate.expire(key,30, TimeUnit.MINUTES);
        BufferedImage image = SecurityImage.createImage(code);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
    }

}
