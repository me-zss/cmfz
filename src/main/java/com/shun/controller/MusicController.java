package com.shun.controller;

import com.shun.entity.Music;
import com.shun.service.MusicService;
import com.shun.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("music")
public class MusicController {
    @Autowired
    private MusicService musicService;
    @RequestMapping("findAll")
    public Map findAll(Integer page,Integer rows) {
        return  musicService.findAll(page, rows);
    }
    @RequestMapping("edit")
    public Map edit(String oper,Music music,String[] id) {
        Map map = null;
        if(oper.equals("add")){
            map = musicService.add(music);
        } else if (oper.equals("edit")) {
            music.setPic(null).setCreateTime(null);
            map = musicService.update(music);
        } else if (oper.equals("del")) {
            map = musicService.del(id);
        }
        return map;
    }
    @RequestMapping("upload")
    public Map upload(MultipartFile pic, String musicId, HttpServletRequest request) throws IOException {
        String uri = null;
        try {
            uri = UploadUtil.getUrl("/music",pic,request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        musicService.update(new Music().setId(musicId).setPic(uri));
        map.put("status","ok");
        return map;
    }
}
