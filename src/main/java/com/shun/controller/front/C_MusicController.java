package com.shun.controller.front;

import com.shun.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("musicPage")
public class C_MusicController {
    @Autowired
    private MusicService musicService;
    @RequestMapping("detail")
    public Map detail(String uid,String id) {
        return musicService.findById(id);
    }
}
